package com.yjotdev.ortografiamariamel.domain.usecase

import javax.inject.Inject
import com.yjotdev.ortografiamariamel.domain.model.Game2Model
import com.yjotdev.ortografiamariamel.domain.repository.GamesRepository

class GetGame2DataUseCase @Inject constructor(
    private val gamesRepository: GamesRepository
) {
    suspend operator fun invoke(unit: Int): List<Game2Model> {
        return gamesRepository.getGame2Data(unit)
    }
}