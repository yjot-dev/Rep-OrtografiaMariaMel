package com.yjotdev.ortografiamariamel.domain.usecase

import javax.inject.Inject
import com.yjotdev.ortografiamariamel.domain.model.Game3Model
import com.yjotdev.ortografiamariamel.domain.repository.GamesRepository

class GetGame3DataUseCase @Inject constructor(
    private val gamesRepository: GamesRepository
) {
    suspend operator fun invoke(unit: Int): List<Game3Model> {
        return gamesRepository.getGame3Data(unit)
    }
}