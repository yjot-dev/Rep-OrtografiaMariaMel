package com.yjotdev.ortografiamariamel.utils.di

import dagger.Module
import dagger.Binds
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton
import com.yjotdev.ortografiamariamel.domain.repository.GamesRepository
import com.yjotdev.ortografiamariamel.data.di.DiModules
import com.yjotdev.ortografiamariamel.utils.repository.FakeGamesRepositoryImpl

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DiModules::class] // Nombre del módulo real
)
@Suppress("unused")
abstract class DiModulesTest {
    @Binds
    @Singleton
    abstract fun bindFakeGamesRepository(
        impl: FakeGamesRepositoryImpl
    ): GamesRepository
}