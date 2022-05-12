package com.bugima.catfacts.domain.repository

import com.bugima.catfacts.domain.model.CatFact
import com.bugima.catfacts.util.Resource
import kotlinx.coroutines.flow.Flow

interface CatFactsRepository {

    suspend fun getCatFacts(
        amount: Int = 30
    ): Flow<Resource<List<CatFact>>>

    suspend fun getCatFactById(
        id: String
    ): Flow<Resource<CatFact>>
}