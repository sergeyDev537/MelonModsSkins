package com.playground.modmelonskins.fragments.skins

import android.app.Application
import androidx.lifecycle.*
import com.playground.modmelonskins.R
import com.playground.modmelonskins.domain.entities.SkinEntity
import com.playground.modmelonskins.domain.usecases.GetListSkinsUseCase
import com.playground.modmelonskins.fragments.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SkinsViewModel @Inject constructor(
    application: Application,
    private val getListSkinsUseCase: GetListSkinsUseCase
) : BaseViewModel(application) {

    private var _listSkins = MutableLiveData<List<SkinEntity>>()
    val listSkins: LiveData<List<SkinEntity>> = _listSkins

    private var _listSkinsErrors = MutableLiveData<String>()
    val listSkinsErrors: LiveData<String> = _listSkinsErrors

    init {
        getListSkins()
    }

    private fun getListSkins(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = getListSkinsUseCase()
                if (result.isEmpty()){
                    _listSkinsErrors.postValue(context.getString(R.string.error_list_empty))
                }
                else{
                    _listSkins.postValue(result)
                }
            }
            catch (e: Exception){
                _listSkinsErrors.value = e.message
            }

        }
    }
}