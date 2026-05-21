package com.yjotdev.ortografiamariamel.domain.usecase

import javax.inject.Inject
import com.yjotdev.ortografiamariamel.domain.model.Game1Model
import com.yjotdev.ortografiamariamel.domain.repository.GamesRepository

class GetGame1DataUseCase @Inject constructor(
    private val gamesRepository: GamesRepository
) {
    suspend operator fun invoke(unit: Int): List<Game1Model>{
        return gamesRepository.getGame1Data(unit)
    }
}