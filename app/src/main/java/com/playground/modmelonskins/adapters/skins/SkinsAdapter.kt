package com.playground.modmelonskins.adapters.skins

import com.google.android.ads.nativetemplates.TemplateView
import com.playground.modmelonskins.adapters.base.BaseAdapter
import com.playground.modmelonskins.adapters.base.BaseViewHolder
import com.playground.modmelonskins.databinding.ItemModsSkinsBinding
import com.playground.modmelonskins.databinding.ItemSmallNativeBinding
import com.playground.modmelonskins.domain.entities.ModEntity
import com.playground.modmelonskins.domain.entities.SkinEntity
import com.playground.modmelonskins.extensions.loadImage

class SkinsAdapter: BaseAdapter<Any, ItemModsSkinsBinding>(ItemModsSkinsBinding::inflate) {

    var clickItemSkins: ((Int?) -> Unit)? = null
    var loadNative: ((TemplateView) -> Unit)? = null

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val itemSkin = getItem(position)
        val binding = (holder.binding)
        when(itemSkin) {
            is SkinEntity -> {
                when (binding) {
                    is ItemModsSkinsBinding -> {
                        val context = binding.root.context
                        binding.tvTitle.text = itemSkin.name
                        itemSkin?.imagesPath?.get(0)?.let {
                            binding.constraintItem.loadImage(context, it)
                        }
                        binding.constraintItem.setOnClickListener {
                            clickItemSkins?.invoke(itemSkin.id)
                        }
                    }
                }
            }
            else -> {
                when(binding){
                    is ItemSmallNativeBinding -> {
                        loadNative?.invoke(binding.myTemplate)
                    }
                }
            }
        }

    }
}