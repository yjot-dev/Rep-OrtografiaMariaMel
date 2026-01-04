package com.yjotdev.ortografiamariamel.utils.di

import dagger.Module
import dagger.Binds
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton
import com.yjotdev.ortografiamariamel.domain.port.GamesPort
import com.yjotdev.ortografiamariamel.infrastructure.di.DiModules
import com.yjotdev.ortografiamariamel.utils.repository.FakeGamesRepository

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
        impl: FakeGamesRepository
    ): GamesPort
}