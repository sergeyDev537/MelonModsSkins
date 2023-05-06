package com.playground.modmelonskins.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.playground.modmelonskins.domain.entities.ModEntity
import com.playground.modmelonskins.domain.entities.SkinEntity
import com.playground.modmelonskins.domain.usecases.GetItemModUseCase
import com.playground.modmelonskins.domain.usecases.GetItemSkinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getItemModUseCase: GetItemModUseCase,
    private val getItemSkinUseCase: GetItemSkinUseCase
) : ViewModel() {

    private var _itemMods = MutableLiveData<ModEntity>()
    val itemMods: LiveData<ModEntity> = _itemMods

    private var _itemSkins = MutableLiveData<SkinEntity>()
    val itemSkins: LiveData<SkinEntity> = _itemSkins

    fun getItemMod(id: Int){
        val result = getItemModUseCase(id)
        result?.let {
            _itemMods.value = it
        } //TODO ERRORS
    }

    fun getItemSkin(id: Int){
        val result = getItemSkinUseCase(id)
        result?.let {
            _itemSkins.value = it
        } //TODO ERRORS
    }

}