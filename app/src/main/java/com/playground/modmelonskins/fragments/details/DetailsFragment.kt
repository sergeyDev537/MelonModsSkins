package com.playground.modmelonskins.fragments.details

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.playground.modmelonskins.R
import com.playground.modmelonskins.databinding.FragmentDetailsBinding
import com.playground.modmelonskins.domain.entities.ModEntity
import com.playground.modmelonskins.domain.entities.SkinEntity
import com.playground.modmelonskins.extensions.convertDpToPx
import com.playground.modmelonskins.extensions.loadImage
import com.playground.modmelonskins.extensions.showSnackBar
import com.playground.modmelonskins.firebase.FirebaseManager
import com.playground.modmelonskins.fragments.base.BaseFragment
import com.playground.modmelonskins.fragments.base.BaseFragmentDetails
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment :
    BaseFragmentDetails<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val args by navArgs<DetailsFragmentArgs>()
    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAGING", "TYPE: ${args.type}, ARGS: ${args.id}")
        parseArgs()
        detailsViewModel.apply {
            setObservable()
            loadNative(binding.adsTemplateSmallHeader.myTemplate)
            loadNative(binding.adsTemplateSmallFooter.myTemplate)
            //loadBanner(binding.adsBanner)
        }
        binding.apply {
            setClickListeners()
        }

    }

    private fun setSettingsToolbar(title: String?) {
        title?.let {
            detailsToolbarListener?.setDetailsToolbar(it)
        } ?: detailsToolbarListener?.setDetailsToolbar(
            requireContext().getString(R.string.title_details)
        )
    }

    private fun parseArgs() {
        when (args.type) {
            FirebaseManager.FILE_MODS_JSON -> {
                detailsViewModel.getItemMod(args.id)
            }
            FirebaseManager.FILE_SKINS_JSON -> {
                detailsViewModel.getItemSkin(args.id)
            }
            else -> {
                detailsViewModel.setError()
            }
        }
    }

    private fun DetailsViewModel.setObservable() {
        itemMods.observe(viewLifecycleOwner) {
            binding.setData(it)
            setSettingsToolbar(it.name)
            showError(false)
        }

        itemSkins.observe(viewLifecycleOwner) {
            binding.setData(it)
            setSettingsToolbar(it.name)
            showError(false)
        }

        itemError.observe(viewLifecycleOwner) {
            binding.root.showSnackBar(it)
            showError(true)
        }
    }

    private fun FragmentDetailsBinding.setData(entity: Any) {
        if (entity is ModEntity) {
            entity.imagesPath?.get(0)?.let {
                ivMainItem.loadImage(requireContext(), it)
            }
            tvItemName.text = entity.name
            tvItemDescription.text = entity.description
            entity.imagesPath?.let {
                loadListImages(it.subList(1, it.size))
            }
        } else if (entity is SkinEntity) {
            entity.imagesPath?.get(0)?.let {
                ivMainItem.loadImage(requireContext(), it)
            }
            tvItemName.text = entity.name
            tvItemDescription.text = entity.description
            entity.imagesPath?.let {
                loadListImages(it.subList(1, it.size))
            }
        }
    }

    private fun loadListImages(listPathImages: List<String>) {
        for (itemImage in listPathImages) {
            val imageView = ImageView(requireContext())
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                200L.convertDpToPx(requireContext())
            )
            params.setMargins(
                0,
                0,
                0,
                20L.convertDpToPx(requireContext())
            )
            imageView.layoutParams = params
            imageView.loadImage(requireContext(), itemImage)
            binding.llImages.addView(imageView)
        }
    }

    private fun showError(boolean: Boolean) {
        binding.apply {
            constraintError.isVisible = boolean
            constraintItem.isGone = boolean
        }
    }

    private fun FragmentDetailsBinding.setClickListeners() {
        buttonNextPage.setOnClickListener {
            detailsViewModel.itemMods.value?.let { mod ->
                mod.pathFile?.let { file ->
                    mod.imagesPath?.get(0)?.let { image ->
                        nextToPreDownload(file, image)
                    }
                }
            } ?: run {
                nextToPreDownload(
                    detailsViewModel.itemSkins.value?.pathFile ?: "",
                    detailsViewModel.itemSkins.value?.imagesPath?.get(0) ?: ""
                )
            }
        }
    }

    private fun nextToPreDownload(pathFile: String, pathImage: String) {
        findNavController().navigate(
            DetailsFragmentDirections.actionDetailsFragmentToHowInstallFragment(
                nameItem = binding.tvItemName.text.toString(),
                pathFile = pathFile,
                pathImage = pathImage,
                type = args.type
            )
        )
    }

}