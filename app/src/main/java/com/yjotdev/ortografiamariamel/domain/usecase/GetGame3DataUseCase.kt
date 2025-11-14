package com.yjotdev.ortografiamariamel.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import com.yjotdev.ortografiamariamel.domain.entity.Game3Entity
import com.yjotdev.ortografiamariamel.domain.port.GamesPort

@Singleton
class GetGame3DataUseCase @Inject constructor(
    private val gamesPort: GamesPort
) {
    suspend operator fun invoke(unit: Int): List<Game3Entity> {
        return gamesPort.getGame3Data(unit)
    }
}