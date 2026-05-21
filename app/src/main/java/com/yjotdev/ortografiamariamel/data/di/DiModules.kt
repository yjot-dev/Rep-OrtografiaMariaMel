package com.yjotdev.ortografiamariamel.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.Binds
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent
import com.yjotdev.ortografiamariamel.domain.repository.GamesRepository
import com.yjotdev.ortografiamariamel.data.repository.GamesRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
@Suppress("unused")
abstract class DiModules {
    @Binds
    @Singleton
    abstract fun bindGamesRepository(
        impl: GamesRepositoryImpl
    ): GamesRepository
}