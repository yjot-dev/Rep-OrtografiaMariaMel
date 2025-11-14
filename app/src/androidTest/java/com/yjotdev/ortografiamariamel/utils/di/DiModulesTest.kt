package com.yjotdev.ortografiamariamel.utils.di

import android.content.Context
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import dagger.Module
import dagger.Provides
import dagger.Binds
import dagger.hilt.android.qualifiers.ApplicationContext
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

    companion object {
        @Provides
        @Singleton
        fun provideTestNavHostController(@ApplicationContext context: Context) =
            TestNavHostController(context).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
    }
}