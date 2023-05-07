package com.playground.modmelonskins.fragments.how_install

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.playground.modmelonskins.databinding.FragmentHowInstallBinding
import com.playground.modmelonskins.extensions.loadImage
import com.playground.modmelonskins.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HowInstallFragment : BaseFragment<FragmentHowInstallBinding>(
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