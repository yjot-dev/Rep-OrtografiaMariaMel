package com.yjotdev.ortografiamariamel.utils.repository

import javax.inject.Inject
import javax.inject.Singleton
import com.yjotdev.ortografiamariamel.domain.entity.Game1Entity
import com.yjotdev.ortografiamariamel.domain.entity.Game2Entity
import com.yjotdev.ortografiamariamel.domain.entity.Game3Entity
import com.yjotdev.ortografiamariamel.domain.port.GamesPort
import com.yjotdev.ortografiamariamel.infrastructure.datasource.DataSourceGame1
import com.yjotdev.ortografiamariamel.infrastructure.datasource.DataSourceGame2
import com.yjotdev.ortografiamariamel.infrastructure.datasource.DataSourceGame3

@Singleton
class FakeGamesRepository @Inject constructor()
    : GamesPort {
    override suspend fun getGame1Data(unit: Int): List<Game1Entity> {
        return when(unit) {
            1 -> DataSourceGame1.listPairOfCardsUnit1
            else -> emptyList()
        }
    }

    override suspend fun getGame2Data(unit: Int): List<Game2Entity> {
        return when(unit) {
            1 -> DataSourceGame2.listQuestionAndAnswersUnit1
            else -> emptyList()
        }
    }

    override suspend fun getGame3Data(unit: Int): List<Game3Entity> {
        return when(unit) {
            1 -> DataSourceGame3.listQuestionAndAnswersUnit1
            else -> emptyList()
        }
    }
}