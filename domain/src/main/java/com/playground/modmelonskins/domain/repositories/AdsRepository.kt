package com.playground.modmelonskins.domain.repositories

import android.app.Activity
import android.view.ViewGroup

interface AdsRepository {

    fun loadNative(viewGroup: ViewGroup)

    fun loadBanner(viewGroup: ViewGroup)

    fun loadInterstitial(activity: Activity, callback: () -> Unit)

}