package com.playground.modmelonskins.domain.usecases

import com.playground.modmelonskins.domain.entities.ModEntity
import com.playground.modmelonskins.domain.repositories.ModsRepository

class GetItemModUseCase(private val modsRepository: ModsRepository) {

    suspend operator fun invoke(id: Int): ModEntity {
        return modsRepository.getItemMod(id)
    }

}