package com.playground.modmelonskins.data.impl

import android.view.ViewGroup
import com.playground.modmelonskins.ads.managers.BannerManager
import com.playground.modmelonskins.ads.managers.NativeManager
import com.playground.modmelonskins.domain.repositories.AdsRepository

class AdsRepositoryImpl(
    private val nativeManager: NativeManager,
    private val bannerManager: BannerManager
): AdsRepository {

    override fun loadNative(viewGroup: ViewGroup) {
        nativeManager.loadNative(viewGroup)
    }

    override fun loadBanner(viewGroup: ViewGroup) {
        bannerManager.loadBanner(viewGroup)
    }
}