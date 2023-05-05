package com.playground.modmelonskins.adapters.mods

import com.playground.modmelonskins.adapters.base.BaseAdapter
import com.playground.modmelonskins.adapters.base.BaseViewHolder
import com.playground.modmelonskins.databinding.ItemModsSkinsBinding
import com.playground.modmelonskins.domain.entities.ModEntity
import com.playground.modmelonskins.extensions.loadImage

class ModsAdapter: BaseAdapter<ModEntity, ItemModsSkinsBinding>(ItemModsSkinsBinding::inflate) {

    var clickItemMods: ((Int?) -> Unit)? = null

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val itemCategory = getItem(position)
        val binding = (holder.binding) as ItemModsSkinsBinding
        val context = binding.root.context
        binding.tvTitle.text = itemCategory.name
        itemCategory?.imagesPath?.get(0)?.let {
            binding.constraintItem.loadImage(context, it)
        }
        binding.constraintItem.setOnClickListener {
            clickItemMods?.invoke(itemCategory.id)
        }
    }
}