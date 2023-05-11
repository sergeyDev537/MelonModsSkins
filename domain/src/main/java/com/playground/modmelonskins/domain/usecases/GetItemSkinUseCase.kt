package com.playground.modmelonskins.domain.usecases

import com.playground.modmelonskins.domain.entities.SkinEntity
import com.playground.modmelonskins.domain.repositories.SkinsRepository

class GetItemSkinUseCase(private val skinsRepository: SkinsRepository) {

    operator fun invoke(id: Int): SkinEntity? {
        return skinsRepository.getItemSkin(id)
    }

}