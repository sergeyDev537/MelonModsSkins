package com.playground.modmelonskins.domain.usecases

import com.playground.modmelonskins.domain.repositories.DownloadRepository
import com.playground.modmelonskins.domain.repositories.ModsRepository

class DownloadItemUseCase(private val downloadRepository: DownloadRepository) {

    suspend operator fun invoke(path: String):Boolean{
        return downloadRepository.downloadFile(path)
    }

}