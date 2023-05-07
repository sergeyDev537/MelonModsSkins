package com.playground.modmelonskins.activities

import androidx.lifecycle.ViewModel
import com.google.android.gms.ads.AdView
import com.playground.modmelonskins.domain.usecases.LoadBannerAdsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadBannerAdsUseCase: LoadBannerAdsUseCase
) : ViewModel() {

    fun loadBanner(adView: AdView){
        loadBannerAdsUseCase(adView)
    }

}