package com.playground.modmelonskins.fragments.dialogs.open

import android.app.Activity
import android.app.Application
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.playground.modmelonskins.domain.usecases.ShowInterstitialAdsUseCase
import com.playground.modmelonskins.domain.usecases.LoadNativeAdsUseCase
import com.playground.modmelonskins.fragments.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DialogOpenItemViewModel @Inject constructor(
    application: Application,
    private val loadNativeAdsUseCase: LoadNativeAdsUseCase,
    private val showInterstitialAdsUseCase: ShowInterstitialAdsUseCase,
): BaseViewModel(application) {

    private var _nextScreen = MutableLiveData<Unit>()
    val nextScreen: LiveData<Unit> = _nextScreen

    fun loadNative(viewGroup: ViewGroup){
        loadNativeAdsUseCase(viewGroup)
    }

    fun loadInterstitial(activity: Activity){
        showInterstitialAdsUseCase(activity) {
            _nextScreen.value = Unit
        }
    }

}