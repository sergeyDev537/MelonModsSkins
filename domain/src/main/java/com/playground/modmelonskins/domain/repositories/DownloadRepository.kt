package com.playground.modmelonskins.domain.repositories

interface DownloadRepository {

    suspend fun downloadFile(path: String): Long
    fun getDownloadStatus(id: Long): Int

}