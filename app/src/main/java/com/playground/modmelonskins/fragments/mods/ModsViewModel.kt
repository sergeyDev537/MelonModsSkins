package com.playground.modmelonskins.fragments.mods

import android.app.Application
import androidx.lifecycle.*
import com.playground.modmelonskins.R
import com.playground.modmelonskins.domain.entities.ModEntity
import com.playground.modmelonskins.domain.usecases.GetListModsUseCase
import com.playground.modmelonskins.fragments.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModsViewModel @Inject constructor(
    application: Application,
    private val getListModsUseCase: GetListModsUseCase
) : BaseViewModel(application) {

    private var _listMods = MutableLiveData<List<ModEntity>>()
    val listMods: LiveData<List<ModEntity>> = _listMods

    private var _listModsErrors = MutableLiveData<String>()
    val listModsErrors: LiveData<String> = _listModsErrors

    init {
        getListMods()
    }

    private fun getListMods(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = getListModsUseCase()
                if (result.isEmpty()){
                    _listModsErrors.postValue(context.getString(R.string.error_list_empty))
                }
                else{
                    _listMods.postValue(result)
                }
            }
            catch (e: Exception){
                _listModsErrors.postValue(e.message)
            }
        }
    }
}