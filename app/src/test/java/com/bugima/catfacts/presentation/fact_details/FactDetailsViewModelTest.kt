package com.bugima.catfacts.presentation.fact_details

import com.bugima.catfacts.domain.model.CatFact
import com.bugima.catfacts.domain.use_case.GetCatFactByIdUseCase
import com.bugima.catfacts.presentation.InstantExecutorExtension
import com.bugima.catfacts.util.Resource
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class, InstantExecutorExtension::class)
class CatFactsViewModelTest {

    @MockK
    private lateinit var useCase: GetCatFactByIdUseCase

    private lateinit var viewModel: FactDetailsViewModel

    @BeforeEach
    fun setup() {
        viewModel = FactDetailsViewModelImpl(useCase)
    }

    @AfterAll
    fun unMockk() = unmockkAll()

    @Test
    fun `should load cat fact`() = runBlocking {
        //GIVEN
        val id = "id"
        val catFactResource = Resource.Success(CatFact(id, "", ""))
        every { runBlocking { useCase.invoke(any()) } } returns catFactResource

        //WHEN
        viewModel.fact.observeForever {
            viewModel.loadFact(id)
            //THEN
            assertThat(it).isEqualTo(catFactResource)
        }
    }
}