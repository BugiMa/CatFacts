package com.bugima.catfacts.domain.use_case

import com.bugima.catfacts.domain.model.CatFact
import com.bugima.catfacts.util.Resource

interface GetCatFactByIdUseCase {
    suspend operator fun invoke(id: String): Resource<CatFact>
}