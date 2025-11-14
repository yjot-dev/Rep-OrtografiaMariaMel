package com.yjotdev.ortografiamariamel.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import com.yjotdev.ortografiamariamel.domain.entity.Game1Entity
import com.yjotdev.ortografiamariamel.domain.port.GamesPort

@Singleton
class GetGame1DataUseCase @Inject constructor(
    private val gamesPort: GamesPort
) {
    suspend operator fun invoke(unit: Int): List<Game1Entity>{
        return gamesPort.getGame1Data(unit)
    }
}