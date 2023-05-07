package com.playground.modmelonskins.fragments.details

import android.app.Application
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.ads.nativetemplates.TemplateView
import com.playground.modmelonskins.R
import com.playground.modmelonskins.domain.entities.ModEntity
import com.playground.modmelonskins.domain.entities.SkinEntity
import com.playground.modmelonskins.domain.usecases.GetItemModUseCase
import com.playground.modmelonskins.domain.usecases.GetItemSkinUseCase
import com.playground.modmelonskins.domain.usecases.LoadBannerAdsUseCase
import com.playground.modmelonskins.domain.usecases.LoadNativeAdsUseCase
import com.playground.modmelonskins.fragments.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    application: Application,
    private val getItemModUseCase: GetItemModUseCase,
    private val getItemSkinUseCase: GetItemSkinUseCase,
    private val loadNativeAdsUseCase: LoadNativeAdsUseCase
) : BaseViewModel(application) {

    private var _itemMods = MutableLiveData<ModEntity>()
    val itemMods: LiveData<ModEntity> = _itemMods

    private var _itemSkins = MutableLiveData<SkinEntity>()
    val itemSkins: LiveData<SkinEntity> = _itemSkins

    private var _itemError = MutableLiveData<String>()
    val itemError: LiveData<String> = _itemError

    fun getItemMod(id: Int){
        val result = getItemModUseCase(id)
        result?.let {
            _itemMods.value = it
        } ?: run {
            _itemError.value = context.getString(R.string.error_not_found_item_id)
        }
    }

    fun getItemSkin(id: Int){
        val result = getItemSkinUseCase(id)
        result?.let {
            _itemSkins.value = it
        } ?: run {
            _itemError.value = context.getString(R.string.error_not_found_item_id)
        }
    }

    fun loadNative(viewGroup: ViewGroup){
        loadNativeAdsUseCase(viewGroup)
    }

    fun setError(){
        _itemError.value = context.getString(R.string.error_not_found_item_id)
    }



}