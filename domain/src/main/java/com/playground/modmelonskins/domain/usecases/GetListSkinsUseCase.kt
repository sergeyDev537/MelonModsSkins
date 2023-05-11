package com.playground.modmelonskins.domain.usecases

import com.playground.modmelonskins.domain.entities.SkinEntity
import com.playground.modmelonskins.domain.repositories.SkinsRepository

class GetListSkinsUseCase(private val skinsRepository: SkinsRepository) {

    suspend operator fun invoke():List<SkinEntity>{
        return skinsRepository.getSkinsList()
    }

}