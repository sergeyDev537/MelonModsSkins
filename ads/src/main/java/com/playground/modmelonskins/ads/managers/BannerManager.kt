package com.playground.modmelonskins.ads.managers

import android.view.ViewGroup
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.playground.modmelonskins.ads.Constants

class BannerManager {

    fun loadBanner(viewGroup: ViewGroup){
        val adView = viewGroup as AdView
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

}