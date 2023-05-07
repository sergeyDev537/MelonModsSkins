package com.playground.modmelonskins.data.impl

import android.app.DownloadManager
import android.content.Context
import android.provider.Settings.Global.getString
import com.playground.modmelonskins.data.R
import com.playground.modmelonskins.data.manager.downloader.AndroidDownloader
import com.playground.modmelonskins.domain.entities.DownloadStatus
import com.playground.modmelonskins.domain.repositories.DownloadRepository

class DownloadRepositoryImpl(
    private val downloader: AndroidDownloader
): DownloadRepository {

    override suspend fun downloadFile(path: String): Long {
        val id = downloader.downloadFile(path)
        return id
    }

    override fun getDownloadStatus(id: Long): Int {
        return downloader.getStatusDownload(id)
    }
}