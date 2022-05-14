package com.bugima.catfacts.domain.use_case

import com.bugima.catfacts.domain.model.CatFact
import com.bugima.catfacts.util.Resource

interface GetCatFactsUseCase {
    suspend operator fun invoke(amount: Int): Resource<List<CatFact>>
}