package com.bugima.catfacts.data.remote

import com.bugima.catfacts.data.remote.dto.CatFactDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatFactsApi {

    @GET("/facts/random")
    suspend fun getCatFacts(
        @Query("amount") amount: Int = 30
    ):Response<List<CatFactDto>>
}