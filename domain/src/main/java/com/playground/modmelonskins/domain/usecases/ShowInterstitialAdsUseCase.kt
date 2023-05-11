package com.playground.modmelonskins.domain.usecases

import android.app.Activity
import com.playground.modmelonskins.domain.repositories.AdsRepository

class ShowInterstitialAdsUseCase(private val adsRepository: AdsRepository) {

    operator fun invoke(activity: Activity, callback: () -> Unit){
        return adsRepository.showInterstitial(activity, callback)
    }

}