package com.playground.modmelonskins.data.manager.downloader

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.webkit.MimeTypeMap
import com.playground.modmelonskins.firebase.FirebaseManager
import java.io.File

class AndroidDownloader(
    private val context: Context,
    private val firebaseManager: FirebaseManager
) : Downloader {

    private val downloadManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        context.getSystemService(DownloadManager::class.java)
    } else {
        context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    }

    override fun downloadFile(url: String): Long {
        val fileName = getFileNameFromUri(url)
        val request = DownloadManager.Request(Uri.parse(url))
            .setMimeType(parseMimeType(url))
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle(fileName)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
        return downloadManager.enqueue(request)
    }

    private fun parseMimeType(url: String): String {
        val file = File(url)
        val map = MimeTypeMap.getSingleton()
        val ext = MimeTypeMap.getFileExtensionFromUrl(file.name)
        var type = map.getMimeTypeFromExtension(ext)
        type = type ?: "*/*"
        return type
    }

    @SuppressLint("Range")
    fun getStatusDownload(downloadId: Long): Int {
        var lastStatus = -1
        var downloading = true
        val query = DownloadManager.Query().setFilterById(downloadId)
        while (downloading) {
            val cursor: Cursor = downloadManager.query(query)
            cursor.moveToFirst()
            if (cursor.getInt(
                    cursor.getColumnIndex(
                        DownloadManager.COLUMN_STATUS
                    )
                ) == DownloadManager.STATUS_SUCCESSFUL ||
                cursor.getInt(
                    cursor.getColumnIndex(
                        DownloadManager.COLUMN_STATUS
                    )
                ) == DownloadManager.STATUS_FAILED
            ) {
                downloading = false
            }
            val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
            if (status != lastStatus){
                lastStatus = status
            }
            cursor.close()
        }
        return lastStatus
    }

    private fun getFileNameFromUri(url: String): String {
        return firebaseManager.getNameFile(url)
    }
}