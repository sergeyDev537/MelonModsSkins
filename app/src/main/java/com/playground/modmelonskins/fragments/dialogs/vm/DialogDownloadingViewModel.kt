package com.playground.modmelonskins.fragments.dialogs.vm

import android.annotation.SuppressLint
import android.app.Application
import android.app.DownloadManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.playground.modmelonskins.R
import com.playground.modmelonskins.domain.entities.DownloadStatus
import com.playground.modmelonskins.domain.usecases.DownloadItemUseCase
import com.playground.modmelonskins.domain.usecases.DownloadStatusUseCase
import com.playground.modmelonskins.firebase.FirebaseManager
import com.playground.modmelonskins.fragments.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DialogDownloadingViewModel @Inject constructor(
    application: Application,
    private val downloadItemUseCase: DownloadItemUseCase,
    private val downloadStatusUseCase: DownloadStatusUseCase,
) : BaseViewModel(application) {

    private var _downloadError = MutableLiveData<String>()
    val downloadError: LiveData<String> = _downloadError

    private var _statusDownload = MutableLiveData<DownloadStatus>()
    val statusDownload: LiveData<DownloadStatus> = _statusDownload

    fun downloadFile(pathFile: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (pathFile != FirebaseManager.EMPTY_STRING) {
                try {
                    val id = downloadItemUseCase(pathFile)
                    val status = downloadStatusUseCase(id)
                    _statusDownload.postValue(statusMessage(status))
                    Log.d("TAGING", "DOWNLOAD RESULT VM: $id")
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        setError()
                    }
                }
            } else {
                withContext(Dispatchers.Main) {
                    setError()
                }
            }
        }
    }

    private fun statusMessage(status: Int): DownloadStatus {
        when (status) {
            DownloadManager.STATUS_FAILED -> {
                return DownloadStatus(
                    DownloadManager.STATUS_FAILED,
                    R.drawable.ic_success_download,
                    context.getString(R.string.download_failed)
                )
            }
            DownloadManager.STATUS_PAUSED -> {
                return DownloadStatus(
                    DownloadManager.STATUS_PAUSED,
                    R.drawable.ic_success_download,
                    context.getString(R.string.download_paused)
                )
            }
            DownloadManager.STATUS_PENDING -> {
                return DownloadStatus(
                    DownloadManager.STATUS_PENDING,
                    R.drawable.ic_success_download,
                    context.getString(R.string.download_pending)
                )
            }
            DownloadManager.STATUS_RUNNING -> {
                return DownloadStatus(
                    DownloadManager.STATUS_RUNNING,
                    R.drawable.ic_success_download,
                    context.getString(R.string.downloading)
                )
            }
            DownloadManager.STATUS_SUCCESSFUL -> {
                return DownloadStatus(
                    DownloadManager.STATUS_SUCCESSFUL,
                    R.drawable.ic_success_download,
                    context.getString(R.string.saved_download_folder)
                )
            }
            else -> {
                return DownloadStatus(
                    111010,
                    R.drawable.ic_success_download,
                    context.getString(R.string.download_error)
                )
            }
        }
    }

    fun setError() {
        _downloadError.value = context.getString(R.string.error_not_found_item_id)

    }

}