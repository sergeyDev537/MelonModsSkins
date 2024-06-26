package com.playground.modmelonskins.fragments.dialogs.predownload

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.playground.modmelonskins.R
import com.playground.modmelonskins.databinding.DialogPreDownloadingItemBinding
import com.playground.modmelonskins.extensions.showToast
import com.playground.modmelonskins.fragments.base.BaseDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogPreDownloadingFragment : BaseDialogFragment<DialogPreDownloadingItemBinding>(
    DialogPreDownloadingItemBinding::inflate
) {

    private val args by navArgs<DialogPreDownloadingFragmentArgs>()
    private val dialogPreDownloadingViewModel: DialogPreDownloadingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogPreDownloadingViewModel.loadNative(binding.adsTemplateMedium.myTemplate)
        binding.apply {
            setData()
            setClickListeners()
        }
    }

    private fun DialogPreDownloadingItemBinding.setData() {
        titlePreDownloadDialog.text = String.format(
            getString(R.string.want_to_install),
            args.type
        )
    }

    private fun DialogPreDownloadingItemBinding.setClickListeners() {
        buttonDownload.setOnClickListener {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                checkPermissions()
            } else {
                showDownloadingDialog()
            }
        }
    }

    private fun showDownloadingDialog() {
        findNavController().navigate(
            DialogPreDownloadingFragmentDirections
                .actionDialogPreDownloadingFragmentToDialogDownloadFragment(
                    nameItem = args.nameItem,
                    pathFile = args.pathFile
                )
        )
    }

    private fun checkPermissions() {
        activityResultLauncher.launch(
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    private val activityResultLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                showDownloadingDialog()
            } else {
                requireContext().showToast(getString(R.string.permission_not_granted))
            }
        }

}