package com.bugima.catfacts.domain.use_case

import com.bugima.catfacts.domain.mapper.toDomain
import com.bugima.catfacts.domain.model.CatFact
import com.bugima.catfacts.domain.repository.CatFactsRepository
import com.bugima.catfacts.util.Resource
import javax.inject.Inject

class GetCatFactsUseCaseImpl @Inject constructor(
    private val repository: CatFactsRepository
): GetCatFactsUseCase {
    override suspend fun invoke(amount: Int): Resource<List<CatFact>> {
        val response = repository.getCatFacts(amount)
        if (response.isSuccessful) return Resource.Success(response.body()!!.toDomain())
        return Resource.Error(response.message())
    }
}