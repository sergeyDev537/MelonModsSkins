package com.playground.modmelonskins.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.content.ContextCompat
import com.google.android.gms.ads.MobileAds
import com.playground.modmelonskins.R
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MelonApp: Application(){

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this) {}
        createNotificationsChannels()
    }

    private fun createNotificationsChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                getString(R.string.reminders_notification_channel_id),
                getString(R.string.reminders_notification_channel_name),
                NotificationManager.IMPORTANCE_HIGH
            )
            ContextCompat.getSystemService(this, NotificationManager::class.java)
                ?.createNotificationChannel(channel)
        }
    }

}