package com.bugima.catfacts.presentation.fact_listing

import androidx.lifecycle.*
import com.bugima.catfacts.domain.model.CatFact
import com.bugima.catfacts.domain.use_case.GetCatFactsUseCase
import com.bugima.catfacts.util.Constants.FETCHED_FACTS_COUNT
import com.bugima.catfacts.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactListingViewModelImpl @Inject constructor(
    private val getCatFactsUseCase: GetCatFactsUseCase
): ViewModel(), FactListingViewModel {

    override val facts = MutableLiveData<Resource<List<CatFact>>>()

    override fun loadFacts() {
        viewModelScope.launch { getCatFacts() }
    }

    private suspend fun getCatFacts() {
        facts.postValue(Resource.Loading())
        try {
            val response = getCatFactsUseCase.invoke(FETCHED_FACTS_COUNT)
            facts.postValue(response)
        } catch (t: Throwable) {
            facts.postValue(Resource.Error(t.message.toString(), t))
        }
    }
}