package com.yjotdev.ortografiamariamel.infrastructure.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.Binds
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent
import com.yjotdev.ortografiamariamel.domain.port.GamesPort
import com.yjotdev.ortografiamariamel.infrastructure.repository.GamesRepository

@Module
@InstallIn(SingletonComponent::class)
@Suppress("unused")
abstract class DiModules {
    @Binds
    @Singleton
    abstract fun bindGamesRepository(
        impl: GamesRepository
    ): GamesPort
}