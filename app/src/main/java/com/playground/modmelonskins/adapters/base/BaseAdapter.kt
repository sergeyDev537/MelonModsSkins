package com.playground.modmelonskins.adapters.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.playground.modmelonskins.R
import com.playground.modmelonskins.databinding.ItemModsSkinsBinding
import com.playground.modmelonskins.databinding.ItemSmallNativeBinding
import com.playground.modmelonskins.domain.entities.ModEntity
import com.playground.modmelonskins.domain.entities.SkinEntity
import com.playground.modmelonskins.fragments.base.Inflate

abstract class BaseAdapter<T, VB : ViewBinding>(
    private val inflate: Inflate<VB>,
) : ListAdapter<T, BaseViewHolder>(object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
        return oldItem == newItem
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            when (viewType) {
                VIEW_TYPE_MODS_SKINS -> ItemModsSkinsBinding.inflate(inflater, parent, false)
                VIEW_TYPE_ADS -> ItemSmallNativeBinding.inflate(inflater, parent, false)
                else -> throw RuntimeException("Unknown view type $viewType")
            }

//        val binding: ViewBinding = inflate.invoke(
//            LayoutInflater.from(parent.context),
//            parent,
//            false
//        )
        return BaseViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is ModEntity || getItem(position) is SkinEntity){
            VIEW_TYPE_MODS_SKINS
        }
        else{
            VIEW_TYPE_ADS
        }
//        return if (position == 0){
//            VIEW_TYPE_MODS_SKINS
//        }
//        else if (position == 1) {
//            VIEW_TYPE_ADS
//        } else if (position % 3 == 0) {
//            VIEW_TYPE_ADS
//        } else {
//            VIEW_TYPE_MODS_SKINS
//        }
    }

    companion object {
        const val VIEW_TYPE_MODS_SKINS = 0
        const val VIEW_TYPE_ADS = 1

        const val MAX_POOL_SIZE = 15
    }

}