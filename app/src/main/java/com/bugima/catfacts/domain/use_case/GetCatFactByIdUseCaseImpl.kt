package com.bugima.catfacts.domain.use_case

import com.bugima.catfacts.domain.mapper.toDomain
import com.bugima.catfacts.domain.model.CatFact
import com.bugima.catfacts.domain.repository.CatFactsRepository
import com.bugima.catfacts.util.Resource
import javax.inject.Inject

class GetCatFactByIdUseCaseImpl @Inject constructor(
    private val repository: CatFactsRepository
): GetCatFactByIdUseCase {
    override suspend fun invoke(id: String): Resource<CatFact> {
        val response = repository.getCatFactById(id)
        if (response.isSuccessful) return Resource.Success(response.body()!!.toDomain())
        return Resource.Error(response.message())
    }
}