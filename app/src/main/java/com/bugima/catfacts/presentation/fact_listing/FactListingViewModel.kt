package com.bugima.catfacts.presentation.fact_listing

import androidx.lifecycle.LiveData
import com.bugima.catfacts.domain.model.CatFact
import com.bugima.catfacts.util.Resource

interface FactListingViewModel {
    val facts: LiveData<Resource<List<CatFact>>>
    fun loadFacts()
}