package com.playground.modmelonskins.domain.usecases

import android.view.ViewGroup
import com.playground.modmelonskins.domain.repositories.AdsRepository

class LoadNativeAdsUseCase(private val adsRepository: AdsRepository) {

    operator fun invoke(viewGroup: ViewGroup){
        return adsRepository.loadNative(viewGroup)
    }

}