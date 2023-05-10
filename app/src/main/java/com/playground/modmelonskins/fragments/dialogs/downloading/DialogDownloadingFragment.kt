package com.playground.modmelonskins.fragments.dialogs.downloading

import android.app.DownloadManager
import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.playground.modmelonskins.R
import com.playground.modmelonskins.databinding.DialogDownloadingItemBinding
import com.playground.modmelonskins.domain.entities.DownloadStatus
import com.playground.modmelonskins.extensions.showToast
import com.playground.modmelonskins.firebase.FirebaseManager
import com.playground.modmelonskins.fragments.base.BaseDialogCancelableFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogDownloadingFragment : BaseDialogCancelableFragment<DialogDownloadingItemBinding>(
    DialogDownloadingItemBinding::inflate
) {

    private val args by navArgs<DialogDownloadingFragmentArgs>()
    private val dialogDownloadingViewModel: DialogDownloadingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseData()
        binding.apply {
            setData()
            setClickListeners()
        }
        dialogDownloadingViewModel.apply {
            setObservable()
        }
    }

    private fun parseData() {
        if (args.pathFile != FirebaseManager.EMPTY_STRING) {
            dialogDownloadingViewModel.downloadFile(args.pathFile)
        } else {
            dialogDownloadingViewModel.setError()
        }
    }

    private fun DialogDownloadingItemBinding.setData() {
        titleDialogInstall.text = args.nameItem
        tvStatusDownloading.text = getString(R.string.downloading)
        ivCancel.isGone = true
        ivSuccessDownload.isInvisible = true
    }

    private fun DialogDownloadingItemBinding.setClickListeners() {
        ivCancel.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun DialogDownloadingViewModel.setObservable() {
        downloadError.observe(viewLifecycleOwner) {
            requireContext().showToast(it)
            dismiss()
        }
        statusDownload.observe(viewLifecycleOwner) {
            updateData(it)
        }
        showRateDialog.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.dialogRateFragment)
        }
    }

    private fun updateData(downloadStatus: DownloadStatus) {
        binding.apply {
            if (downloadStatus.statusCode == DownloadManager.STATUS_PENDING ||
                downloadStatus.statusCode == DownloadManager.STATUS_RUNNING
            ){
                tvStatusDownloading.text = downloadStatus.message
                ivCancel.isGone = true
                ivSuccessDownload.isInvisible = true
                progressDownloading.isVisible = true
            }
            else{
                tvStatusDownloading.text = downloadStatus.message
                ivCancel.isVisible = true
                downloadStatus.pathIcon?.let {
                    ivSuccessDownload.setImageResource(it)
                    ivSuccessDownload.isVisible = true
                }
                progressDownloading.isInvisible = true
                dialogDownloadingViewModel.checkShowRateDialog()
            }
        }
    }

}