package com.playground.modmelonskins.domain.usecases

import com.playground.modmelonskins.domain.repositories.SkinsRepository

class DownloadSkinsUseCase(private val skinsRepository: SkinsRepository) {

    suspend operator fun invoke(path: String):Boolean{
        return skinsRepository.downloadSkin(path)
    }

}