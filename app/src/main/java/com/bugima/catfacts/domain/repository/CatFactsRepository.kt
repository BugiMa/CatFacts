package com.bugima.catfacts.domain.repository

import com.bugima.catfacts.data.remote.dto.CatFactDetailsDto
import com.bugima.catfacts.data.remote.dto.CatFactListItemDto
import retrofit2.Response

interface CatFactsRepository {

    suspend fun getCatFacts(
        amount: Int = 30
    ): Response<List<CatFactListItemDto>>

    suspend fun getCatFactById(
        id: String
    ): Response<CatFactDetailsDto>
}