package com.bugima.catfacts.data.repository

import com.bugima.catfacts.data.mapper.toDomain
import com.bugima.catfacts.data.remote.CatFactsApi
import com.bugima.catfacts.domain.model.CatFact
import com.bugima.catfacts.domain.repository.CatFactsRepository
import com.bugima.catfacts.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatFactsRepositoryImpl @Inject constructor(
    private val api: CatFactsApi
): CatFactsRepository {
    override suspend fun getCatFacts(amount: Int): Flow<Resource<List<CatFact>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                emit(Resource.Success(api.getFacts(amount).body()?.toDomain()))
            } catch (t: Throwable) {
                emit(Resource.Error(t.stackTraceToString()))
            }
        }
    }

    override suspend fun getCatFactById(id: String): Flow<Resource<CatFact>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                emit(Resource.Success(api.getFactById(id).body()?.toDomain()))
            } catch (t: Throwable) {
                emit(Resource.Error(t.stackTraceToString()))
            }
        }
    }
}