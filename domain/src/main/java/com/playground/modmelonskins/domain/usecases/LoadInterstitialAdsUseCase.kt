package com.playground.modmelonskins.domain.usecases

import android.app.Activity
import android.view.ViewGroup
import com.playground.modmelonskins.domain.repositories.AdsRepository

class LoadInterstitialAdsUseCase(private val adsRepository: AdsRepository) {

    operator fun invoke(activity: Activity, callback: () -> Unit){
        return adsRepository.loadInterstitial(activity, callback)
    }

}