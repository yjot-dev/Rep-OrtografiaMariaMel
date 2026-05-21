package com.yjotdev.ortografiamariamel.domain.repository

import com.yjotdev.ortografiamariamel.domain.model.Game1Model
import com.yjotdev.ortografiamariamel.domain.model.Game2Model
import com.yjotdev.ortografiamariamel.domain.model.Game3Model

interface GamesRepository {
    suspend fun getGame1Data(unit: Int): List<Game1Model>
    suspend fun getGame2Data(unit: Int): List<Game2Model>
    suspend fun getGame3Data(unit: Int): List<Game3Model>
}