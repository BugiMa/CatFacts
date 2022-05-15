package com.bugima.catfacts.presentation.fact_listing

import com.bugima.catfacts.domain.model.CatFact
import com.bugima.catfacts.domain.use_case.GetCatFactsUseCase
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
    private lateinit var useCase: GetCatFactsUseCase

    private lateinit var viewModel: FactListingViewModel

    @BeforeEach
    fun setup() {
        viewModel = FactListingViewModelImpl(useCase)
    }

    @AfterAll
    fun unMockk() = unmockkAll()


    @Test
    fun `should load cat facts`() = runBlocking {
        //GIVEN
        val catFactsResource = Resource.Success(listOf(CatFact("", "", "")))
        every { runBlocking { useCase.invoke(any()) } } returns catFactsResource

        //WHEN
        viewModel.facts.observeForever {
            viewModel.loadFacts()
            //THEN
            assertThat(it).isEqualTo(catFactsResource)
        }
    }
}