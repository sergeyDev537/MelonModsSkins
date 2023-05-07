package com.playground.modmelonskins.domain.usecases

import com.playground.modmelonskins.domain.repositories.DownloadRepository

class DownloadStatusUseCase(private val downloadRepository: DownloadRepository) {

    suspend operator fun invoke(id: Long): Int {
        return downloadRepository.getDownloadStatus(id)
    }

}