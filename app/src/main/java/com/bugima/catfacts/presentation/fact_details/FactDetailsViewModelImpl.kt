package com.bugima.catfacts.presentation.fact_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bugima.catfacts.domain.model.CatFact
import com.bugima.catfacts.domain.use_case.GetCatFactByIdUseCase
import com.bugima.catfacts.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactDetailsViewModelImpl @Inject constructor(
    private val getCatFactByIdUseCase: GetCatFactByIdUseCase
): ViewModel(), FactDetailsViewModel {

    override val fact = MutableLiveData<Resource<CatFact>>()

    override fun loadFact(id: String) {
        viewModelScope.launch { getCatFacts(id) }
    }

    private suspend fun getCatFacts(id: String) {
        fact.postValue(Resource.Loading())
        try {
            val response = getCatFactByIdUseCase.invoke(id)
            fact.postValue(response)
        } catch (t: Throwable) {
            fact.postValue(Resource.Error(t.message.toString(), t))
        }
    }
}