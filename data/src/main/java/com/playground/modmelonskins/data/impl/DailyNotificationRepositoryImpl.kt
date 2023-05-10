package com.playground.modmelonskins.data.impl

import android.content.Intent
import com.playground.modmelonskins.data.manager.daily.RemindersManager
import com.playground.modmelonskins.domain.repositories.DailyNotificationRepository

class DailyNotificationRepositoryImpl(
    private val remindersManager: RemindersManager
): DailyNotificationRepository {

    override fun startDailyNotification(intent: Intent) {
        remindersManager.startReminder(
            intent = intent
        )
    }
}