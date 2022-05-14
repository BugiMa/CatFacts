package com.bugima.catfacts.presentation.fact_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bugima.catfacts.domain.model.CatFact
import com.bugima.catfacts.domain.use_case.GetCatFactByIdUseCase
import com.bugima.catfacts.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class FactDetailsViewModel @Inject constructor(
    private val getCatFactByIdUseCase: GetCatFactByIdUseCase
): ViewModel() {

    private val _fact = MutableLiveData<Resource<CatFact>>()
    val fact: LiveData<Resource<CatFact>> get() = _fact

    fun loadFact(id: String) = viewModelScope.launch { getCatFacts(id) }

    private suspend fun getCatFacts(id: String) {
        _fact.postValue(Resource.Loading())
        try {
            val response = getCatFactByIdUseCase.invoke(id)
            _fact.postValue(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _fact.postValue(Resource.Error("Network Error", t))
                else -> _fact.postValue(Resource.Error("Some Error", t))
            }
        }
    }
}