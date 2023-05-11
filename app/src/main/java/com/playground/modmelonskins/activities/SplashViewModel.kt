package com.playground.modmelonskins.activities

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.playground.modmelonskins.domain.usecases.ShowInterstitialAdsUseCase
import com.playground.modmelonskins.domain.usecases.StartDailyNotificationUseCase
import com.playground.modmelonskins.fragments.base.BaseViewModel
import com.playground.modmelonskins.receivers.AlarmReceiver
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    application: Application,
    private val showInterstitialAdsUseCase: ShowInterstitialAdsUseCase,
    private val dailyNotificationUseCase: StartDailyNotificationUseCase,
) : BaseViewModel(application) {

    private var _endTimer = MutableLiveData<Unit>()
    val endTimer: LiveData<Unit> = _endTimer

    private var _nextScreen = MutableLiveData<Unit>()
    val nextScreen: LiveData<Unit> = _nextScreen

    init {
        startTimer()
        startDailyNotification()
    }

    private fun startTimer() {
        object : CountDownTimer(TIMER_MILLIS, MILLIS) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                _endTimer.value = Unit
            }
        }.start()
    }

    fun showInterstitial(activity: Activity) {
        showInterstitialAdsUseCase(activity) {
            _nextScreen.value = Unit
        }
    }

    private fun startDailyNotification() {
        dailyNotificationUseCase(
            Intent(context.applicationContext, AlarmReceiver::class.java)
        )
    }

    companion object {

        const val TIMER_MILLIS = 5000L
        const val MILLIS = 1000L

    }

}