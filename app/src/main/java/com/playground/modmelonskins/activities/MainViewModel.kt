package com.playground.modmelonskins.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.ads.AdView
import com.playground.modmelonskins.data.manager.state.StateManager
import com.playground.modmelonskins.data.manager.state.StateManager.Companion.DEFAULT_24_HOURS
import com.playground.modmelonskins.data.manager.state.StateManager.Companion.DEFAULT_TIME
import com.playground.modmelonskins.domain.usecases.LoadBannerAdsUseCase
import com.playground.modmelonskins.extensions.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val stateManager: StateManager,
    private val loadBannerAdsUseCase: LoadBannerAdsUseCase
) : ViewModel() {

    private val _showRateDialog: SingleLiveEvent<Unit> = SingleLiveEvent()
    val showRateDialog: SingleLiveEvent<Unit> = _showRateDialog

    init {
        checkShowRate()
    }

    fun loadBanner(adView: AdView){
        loadBannerAdsUseCase(adView)
    }

    fun checkShowRate(){
        if (!stateManager.getShowTime() && calculateTime()){
            showRateDialog.value = Unit
            stateManager.setShowTime()
        }
    }

    private fun calculateTime(): Boolean {
        val savedTime = stateManager.getSavedTime()
        return if (savedTime == DEFAULT_TIME){
            stateManager.saveTime()
            false
        } else{
            (System.currentTimeMillis() - savedTime) > DEFAULT_24_HOURS
        }

    }

}