package com.playground.modmelonskins.ads.managers

import android.content.Context
import android.view.ViewGroup
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.playground.modmelonskins.ads.Constants
import com.playground.modmelonskins.ads.R

class NativeManager(
    private val context: Context
) {

    fun loadNative(viewGroup: ViewGroup){
        val templateView = viewGroup as TemplateView
        val adLoader = AdLoader.Builder(context, context.getString(R.string.key_ads_native))
            .forNativeAd { ad : NativeAd ->
                templateView.setNativeAd(ad)
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(adError: LoadAdError) {}
            })
            .withNativeAdOptions(
                NativeAdOptions.Builder()
                .build())
            .build()
        adLoader.loadAd(AdRequest.Builder().build())
    }

}