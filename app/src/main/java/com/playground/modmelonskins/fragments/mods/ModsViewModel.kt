package com.playground.modmelonskins.fragments.mods

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playground.modmelonskins.domain.entities.ModEntity
import com.playground.modmelonskins.domain.usecases.DownloadModsUseCase
import com.playground.modmelonskins.domain.usecases.GetItemModUseCase
import com.playground.modmelonskins.domain.usecases.GetListModsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModsViewModel @Inject constructor(
    private val getListModsUseCase: GetListModsUseCase,
    private val getItemModUseCase: GetItemModUseCase,
    private val downloadModsUseCase: DownloadModsUseCase
) : ViewModel() {

    private var _listMods = MutableLiveData<List<ModEntity>>()
    val listMods: LiveData<List<ModEntity>> = _listMods

    init {
        getListMods()
    }

    fun getListMods(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = getListModsUseCase()
            _listMods.postValue(result)
        }
    }

}