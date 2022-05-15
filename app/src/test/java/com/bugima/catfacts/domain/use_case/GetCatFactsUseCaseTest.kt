package com.bugima.catfacts.domain.use_case

import com.bugima.catfacts.data.remote.dto.CatFactListItemDto
import com.bugima.catfacts.domain.mapper.toDomain
import com.bugima.catfacts.domain.repository.CatFactsRepository
import com.bugima.catfacts.util.Resource
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import retrofit2.Response

@ExtendWith(MockKExtension::class)
class GetCatFactsUseCaseTest {

    @MockK
    private lateinit var repository: CatFactsRepository

    private lateinit var server: MockWebServer

    private lateinit var getCatFactsUseCaseImpl: GetCatFactsUseCase

    @BeforeEach
    fun setup() {
        getCatFactsUseCaseImpl = GetCatFactsUseCaseImpl(repository)
        server = MockWebServer()
    }

    @Test
    fun `WHEN response is successful THEN return Success`() = runBlocking {
        //GIVEN
        val amount = 1
        val catFactDto = CatFactListItemDto(0, "", "", false, "", CatFactListItemDto.Status(0,""),"","","",false,"")
        val response = Response.success(listOf(catFactDto))
        every { runBlocking { repository.getCatFacts(amount) }} returns response

        //WHEN
        val catFacts = getCatFactsUseCaseImpl.invoke(amount)

        //THEN
        verify(exactly = 1) { runBlocking { repository.getCatFacts(amount) } }
        assertThat((catFacts as Resource.Success).data).isEqualTo(Resource.Success(response.body()?.toDomain()).data)
    }

    @Test
    fun `WHEN response is not successful THEN return Error`() = runBlocking {
        //GIVEN
        val amount = 1
        val errorResponse: Response<List<CatFactListItemDto>> = Response.error(500, "error".toResponseBody(any()))
        every { runBlocking { repository.getCatFacts(amount) }} returns errorResponse

        //WHEN
        val catFact = getCatFactsUseCaseImpl.invoke(amount)

        //THEN
        verify(exactly = 1) { runBlocking { repository.getCatFacts(amount) } }
        assertThat(catFact).isInstanceOf(Resource.Error::class.java)
    }
}