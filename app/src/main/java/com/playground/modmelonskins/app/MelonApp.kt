package com.playground.modmelonskins.app

import android.app.Application
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MelonApp: Application(){

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this) {}
    }
}