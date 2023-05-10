package com.playground.modmelonskins.domain.usecases

import android.content.Intent
import com.playground.modmelonskins.domain.repositories.DailyNotificationRepository

class StartDailyNotificationUseCase(private val dailyNotificationRepository: DailyNotificationRepository) {

    operator fun invoke(intent: Intent) {
        dailyNotificationRepository.startDailyNotification(intent)
    }

}