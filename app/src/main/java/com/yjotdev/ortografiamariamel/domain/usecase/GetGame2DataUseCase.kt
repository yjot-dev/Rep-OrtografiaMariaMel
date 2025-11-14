package com.yjotdev.ortografiamariamel.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import com.yjotdev.ortografiamariamel.domain.entity.Game2Entity
import com.yjotdev.ortografiamariamel.domain.port.GamesPort

@Singleton
class GetGame2DataUseCase @Inject constructor(
    private val gamesPort: GamesPort
) {
    suspend operator fun invoke(unit: Int): List<Game2Entity> {
        return gamesPort.getGame2Data(unit)
    }
}