package com.playground.modmelonskins.domain.usecases

import com.playground.modmelonskins.domain.entities.ModEntity
import com.playground.modmelonskins.domain.repositories.ModsRepository

class GetListModsUseCase(private val modsRepository: ModsRepository) {

    suspend operator fun invoke():List<ModEntity>{
        return modsRepository.getModsList()
    }

}