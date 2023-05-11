package com.playground.modmelonskins.fragments.dialogs.predownload

import android.app.Application
import android.view.ViewGroup
import com.playground.modmelonskins.domain.usecases.LoadNativeAdsUseCase
import com.playground.modmelonskins.fragments.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DialogPreDownloadingViewModel @Inject constructor(
    application: Application,
    private val loadNativeAdsUseCase: LoadNativeAdsUseCase
): BaseViewModel(application) {

    fun loadNative(viewGroup: ViewGroup){
        loadNativeAdsUseCase(viewGroup)
    }

}