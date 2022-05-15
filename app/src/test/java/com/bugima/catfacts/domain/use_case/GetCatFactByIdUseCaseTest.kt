package com.bugima.catfacts.domain.use_case

import com.bugima.catfacts.data.remote.dto.CatFactDetailsDto
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
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import retrofit2.Response

@ExtendWith(MockKExtension::class)
class GetCatFactByIdUseCaseTest {

    @MockK
    private lateinit var repository: CatFactsRepository

    private lateinit var server: MockWebServer

    private lateinit var getCatFactByIdUseCaseImpl: GetCatFactByIdUseCase

    @BeforeEach
    fun setup() {
        getCatFactByIdUseCaseImpl = GetCatFactByIdUseCaseImpl(repository)
        server = MockWebServer()
    }

    @Test
    fun `WHEN response is successful THEN return Success`() = runBlocking {
        //GIVEN
        val id = "id"
        val catFactDto = CatFactDetailsDto(0, id, "", false, "", CatFactListItemDto.Status(0,""),"","","",false, CatFactDetailsDto.User("", CatFactDetailsDto.User.Name("", ""), ""))
        val response = Response.success(catFactDto)
        every { runBlocking { repository.getCatFactById(id) }} returns response

        //WHEN
        val catFacts = getCatFactByIdUseCaseImpl.invoke(id)

        //THEN
        verify(exactly = 1) { runBlocking { repository.getCatFactById(id) } }
        assertThat((catFacts as Resource.Success).data).isEqualTo(Resource.Success(response.body()?.toDomain()).data)
    }

    @Test
    fun `WHEN response is not successful THEN return Error`() = runBlocking {
        //GIVEN
        val id = "id"
        val errorResponse: Response<CatFactDetailsDto> = Response.error(500, "error".toResponseBody(any()))
        every { runBlocking { repository.getCatFactById(id) }} returns errorResponse

        //WHEN
        val catFact = getCatFactByIdUseCaseImpl.invoke(id)

        //THEN
        verify(exactly = 1) { runBlocking { repository.getCatFactById(id) } }
        assertThat(catFact).isInstanceOf(Resource.Error::class.java)
    }
}