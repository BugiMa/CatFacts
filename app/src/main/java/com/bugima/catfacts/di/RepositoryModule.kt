package com.bugima.catfacts.di

import com.bugima.catfacts.data.repository.CatFactsRepositoryImpl
import com.bugima.catfacts.domain.repository.CatFactsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCatFactsRepository(
        catFactsRepositoryImpl: CatFactsRepositoryImpl
    ): CatFactsRepository
}