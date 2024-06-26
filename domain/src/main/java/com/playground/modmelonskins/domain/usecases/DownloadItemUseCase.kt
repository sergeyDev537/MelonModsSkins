package com.playground.modmelonskins.domain.usecases

import com.playground.modmelonskins.domain.repositories.DownloadRepository

class DownloadItemUseCase(private val downloadRepository: DownloadRepository) {

    suspend operator fun invoke(path: String):Long{
        return downloadRepository.downloadFile(path)
    }

}