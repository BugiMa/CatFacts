package com.bugima.catfacts.data.remote

import com.bugima.catfacts.data.remote.dto.CatFactDetailsDto
import com.bugima.catfacts.data.remote.dto.CatFactListItemDto
import retrofit2.Response
import retrofit2.http.*


interface CatFactsApi {

    @GET("facts/random")
    suspend fun getFacts(
        @Query("amount") amount: Int
    ):Response<List<CatFactListItemDto>>

    @GET("facts/{id}")
    suspend fun getFactById(
        @Path("id") id: String
    ):Response<CatFactDetailsDto>
}