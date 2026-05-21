package com.yjotdev.ortografiamariamel.data.repository

import javax.inject.Inject
import javax.inject.Singleton
import com.yjotdev.ortografiamariamel.domain.model.Game1Model
import com.yjotdev.ortografiamariamel.domain.model.Game2Model
import com.yjotdev.ortografiamariamel.domain.model.Game3Model
import com.yjotdev.ortografiamariamel.domain.repository.GamesRepository
import com.yjotdev.ortografiamariamel.data.local.Game1Local
import com.yjotdev.ortografiamariamel.data.local.Game2Local
import com.yjotdev.ortografiamariamel.data.local.Game3Local

@Singleton
class GamesRepositoryImpl @Inject constructor()
    : GamesRepository {
    override suspend fun getGame1Data(unit: Int): List<Game1Model> {
        return when(unit) {
            1 -> Game1Local.listPairOfCardsUnit1
            else -> emptyList()
        }
    }

    override suspend fun getGame2Data(unit: Int): List<Game2Model> {
        return when(unit) {
            1 -> Game2Local.listQuestionAndAnswersUnit1
            else -> emptyList()
        }
    }

    override suspend fun getGame3Data(unit: Int): List<Game3Model> {
        return when(unit) {
            1 -> Game3Local.listQuestionAndAnswersUnit1
            else -> emptyList()
        }
    }
}