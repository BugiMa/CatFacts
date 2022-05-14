package com.bugima.catfacts.presentation.fact_listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bugima.catfacts.domain.model.CatFact
import com.bugima.catfacts.domain.use_case.GetCatFactsUseCase
import com.bugima.catfacts.util.Constants.FETCHED_FACTS_COUNT
import com.bugima.catfacts.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class FactListingViewModel @Inject constructor(
    private val getCatFactsUseCase: GetCatFactsUseCase
): ViewModel() {

    private val _facts = MutableLiveData<Resource<List<CatFact>>>()
    val facts: LiveData<Resource<List<CatFact>>> get() = _facts

    init { loadFacts() }

    fun loadFacts() = viewModelScope.launch(Dispatchers.IO) { getCatFacts() }

    private suspend fun getCatFacts() {
        _facts.postValue(Resource.Loading())
        try {
            val response = getCatFactsUseCase.invoke(FETCHED_FACTS_COUNT)
            _facts.postValue(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _facts.postValue(Resource.Error("Network Error", t))
                else -> _facts.postValue(Resource.Error("Some Error", t))
            }
        }
    }
}