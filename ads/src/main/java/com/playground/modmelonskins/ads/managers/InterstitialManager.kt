package com.playground.modmelonskins.ads.managers

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.playground.modmelonskins.ads.R

class InterstitialManager(private val context: Context) {

    private var mInterstitialAd: InterstitialAd? = null

    init {
        loadInterstitial()
    }

    fun loadInterstitial() {
        if (mInterstitialAd == null){
            val adRequest = AdRequest.Builder().build()
            InterstitialAd.load(
                context,
                context.getString(R.string.key_ads_interstitial),
                adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        Log.d("TAGING", "onAdFailedToLoad")
                        mInterstitialAd = null
                    }

                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        Log.d("TAGING", "onAdLoaded")
                        mInterstitialAd = interstitialAd
                    }
                }
            )
        }
    }

    fun showInterstitial(activity: Activity, callback: () -> Unit) {
        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback(){
            override fun onAdClicked() {
                super.onAdClicked()
            }

            override fun onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent()
                mInterstitialAd = null
                loadInterstitial()
                callback()
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                super.onAdFailedToShowFullScreenContent(p0)
                mInterstitialAd = null
                loadInterstitial()
                callback()
            }

            override fun onAdImpression() {
                super.onAdImpression()
            }

            override fun onAdShowedFullScreenContent() {
                super.onAdShowedFullScreenContent()
            }
        }
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(activity)
        }
    }

}