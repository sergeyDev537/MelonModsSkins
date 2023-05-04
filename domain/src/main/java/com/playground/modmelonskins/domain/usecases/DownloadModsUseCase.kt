package com.playground.modmelonskins.domain.usecases

import com.playground.modmelonskins.domain.repositories.ModsRepository

class DownloadModsUseCase(private val modsRepository: ModsRepository) {

    suspend operator fun invoke(path: String):Boolean{
        return modsRepository.downloadMod(path)
    }

}