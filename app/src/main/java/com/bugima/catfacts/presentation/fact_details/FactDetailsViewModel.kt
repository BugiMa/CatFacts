package com.bugima.catfacts.presentation.fact_details

import androidx.lifecycle.LiveData
import com.bugima.catfacts.domain.model.CatFact
import com.bugima.catfacts.util.Resource

interface FactDetailsViewModel {
    val fact: LiveData<Resource<CatFact>>
    fun loadFact(id: String)
}