package com.bugima.catfacts.di

import com.bugima.catfacts.domain.use_case.GetCatFactByIdUseCase
import com.bugima.catfacts.domain.use_case.GetCatFactByIdUseCaseImpl
import com.bugima.catfacts.domain.use_case.GetCatFactsUseCase
import com.bugima.catfacts.domain.use_case.GetCatFactsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindGetCatFactsUseCase(
        getCatFactsUseCaseImpl: GetCatFactsUseCaseImpl
    ): GetCatFactsUseCase

    @Binds
    @Singleton
    abstract fun bindGetCatFactByIdUseCase(
        getCatFactByIdUseCaseImpl: GetCatFactByIdUseCaseImpl
    ): GetCatFactByIdUseCase
}