package com.playground.modmelonskins.domain.repositories

import android.content.Intent

interface DailyNotificationRepository {

    fun startDailyNotification(intent: Intent)

}