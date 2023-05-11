package com.playground.modmelonskins.fragments.how_install

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.playground.modmelonskins.R
import com.playground.modmelonskins.databinding.FragmentHowInstallBinding
import com.playground.modmelonskins.extensions.loadImage
import com.playground.modmelonskins.fragments.base.BaseFragmentDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HowInstallFragment : BaseFragmentDetails<FragmentHowInstallBinding>(
    FragmentHowInstallBinding::inflate
) {

    private val args by navArgs<HowInstallFragmentArgs>()
    private val howInstallViewModel: HowInstallViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            setData()
            setClickListeners()
        }
        howInstallViewModel.apply {
            loadNative(binding.adsTemplateSmallFooter.myTemplate)
        }

    }

    private fun FragmentHowInstallBinding.setData() {
        ivMainItem.loadImage(requireContext(), args.pathImage)
        setSettingsToolbar(args.nameItem)
    }

    private fun setSettingsToolbar(title: String?) {
        title?.let {
            detailsToolbarListener?.setDetailsToolbar(it)
        } ?: detailsToolbarListener?.setDetailsToolbar(
            requireContext().getString(R.string.title_details)
        )
    }

    private fun FragmentHowInstallBinding.setClickListeners() {
        buttonDownload.setOnClickListener {
            findNavController().navigate(
                HowInstallFragmentDirections.actionHowInstallFragmentToDialogPreDownloadingFragment(
                    nameItem = args.nameItem,
                    pathFile = args.pathFile,
                    type = args.type
                )
            )
        }
    }

}