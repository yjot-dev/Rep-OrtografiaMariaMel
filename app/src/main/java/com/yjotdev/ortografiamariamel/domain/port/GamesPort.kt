package com.yjotdev.ortografiamariamel.domain.port

import com.yjotdev.ortografiamariamel.domain.entity.Game1Entity
import com.yjotdev.ortografiamariamel.domain.entity.Game2Entity
import com.yjotdev.ortografiamariamel.domain.entity.Game3Entity

interface GamesPort {
    suspend fun getGame1Data(unit: Int): List<Game1Entity>
    suspend fun getGame2Data(unit: Int): List<Game2Entity>
    suspend fun getGame3Data(unit: Int): List<Game3Entity>
}