package com.playground.modmelonskins.adapters.mods

import com.google.android.ads.nativetemplates.TemplateView
import com.playground.modmelonskins.adapters.base.BaseAdapter
import com.playground.modmelonskins.adapters.base.BaseViewHolder
import com.playground.modmelonskins.databinding.ItemModsSkinsBinding
import com.playground.modmelonskins.databinding.ItemSmallNativeBinding
import com.playground.modmelonskins.domain.entities.ModEntity
import com.playground.modmelonskins.extensions.loadImage

class ModsAdapter: BaseAdapter<Any, ItemModsSkinsBinding>(ItemModsSkinsBinding::inflate) {

    var clickItemMods: ((Int?) -> Unit)? = null
    var loadNative: ((TemplateView) -> Unit)? = null

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val itemMod = getItem(position)
        val binding = (holder.binding)
        when(itemMod){
            is ModEntity -> {
                when(binding){
                    is ItemModsSkinsBinding -> {
                        val context = binding.root.context
                        binding.tvTitle.text = itemMod.name
                        itemMod.imagesPath?.get(0)?.let {
                            binding.constraintItem.loadImage(context, it)
                        }
                        binding.constraintItem.setOnClickListener {
                            clickItemMods?.invoke(itemMod.id)
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