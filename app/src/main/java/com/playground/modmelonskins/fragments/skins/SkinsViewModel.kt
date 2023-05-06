package com.playground.modmelonskins.fragments.skins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playground.modmelonskins.domain.entities.SkinEntity
import com.playground.modmelonskins.domain.usecases.GetListSkinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SkinsViewModel @Inject constructor(
    private val getListSkinsUseCase: GetListSkinsUseCase
) : ViewModel() {

    private var _listSkins = MutableLiveData<List<SkinEntity>>()
    val listSkins: LiveData<List<SkinEntity>> = _listSkins

    init {
        getListSkins()
    }

    private fun getListSkins(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = getListSkinsUseCase()
            _listSkins.postValue(result)
        }
    }
}