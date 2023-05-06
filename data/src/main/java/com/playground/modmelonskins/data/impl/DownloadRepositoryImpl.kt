package com.playground.modmelonskins.data.impl

import com.playground.modmelonskins.data.manager.downloader.AndroidDownloader
import com.playground.modmelonskins.domain.repositories.DownloadRepository

class DownloadRepositoryImpl(
    private val downloader: AndroidDownloader
): DownloadRepository {

    override suspend fun downloadFile(path: String): Boolean {
        val id = downloader.downloadFile(path)
        return id != -1L
    }
}