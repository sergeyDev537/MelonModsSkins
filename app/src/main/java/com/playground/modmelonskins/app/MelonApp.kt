package com.playground.modmelonskins.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.content.ContextCompat
import com.google.android.gms.ads.MobileAds
import com.google.firebase.FirebaseOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.playground.modmelonskins.R
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MelonApp : Application() {

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this) {}
        createNotificationsChannels()
        initFirebase()
    }

    private fun initFirebase() {
        val options = FirebaseOptions.Builder()
            .setProjectId("melon-mods-skins")
            .setApplicationId("1:1215144303:android:879e265f48a6690f66c89a")
            .build()

        Firebase.initialize(this, options, "Melon Mods Skins")
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