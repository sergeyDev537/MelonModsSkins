package com.playground.modmelonskins.domain.repositories

interface DownloadRepository {

    suspend fun downloadFile(path: String): Boolean

}