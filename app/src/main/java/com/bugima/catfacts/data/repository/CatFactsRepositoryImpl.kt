package com.bugima.catfacts.data.repository

import com.bugima.catfacts.data.remote.CatFactsApi
import com.bugima.catfacts.data.remote.dto.CatFactDetailsDto
import com.bugima.catfacts.data.remote.dto.CatFactListItemDto
import com.bugima.catfacts.domain.repository.CatFactsRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatFactsRepositoryImpl @Inject constructor(
    private val api: CatFactsApi
): CatFactsRepository {

    override suspend fun getCatFacts(amount: Int): Response<List<CatFactListItemDto>> = api.getFacts(amount)

    override suspend fun getCatFactById(id: String): Response<CatFactDetailsDto> = api.getFactById(id)
}