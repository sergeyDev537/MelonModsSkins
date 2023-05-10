package com.playground.modmelonskins.data.manager.daily

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

class RemindersManager(private val context: Context) {

    fun startReminder(
        reminderTime: String = "20:00",
        reminderId: Int = REMINDER_NOTIFICATION_REQUEST_CODE,
        intent: Intent,
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val (hours, min) = reminderTime.split(":").map { it.toInt() }
        val newIntent = intent.let { intent1 ->
                PendingIntent.getBroadcast(
                    context.applicationContext,
                    reminderId,
                    intent1,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            }

        val calendar: Calendar = Calendar.getInstance(Locale.ROOT).apply {
            set(Calendar.HOUR_OF_DAY, hours)
            set(Calendar.MINUTE, min)
        }

        if (Calendar.getInstance(Locale.ROOT)
                .apply { add(Calendar.MINUTE, 1) }.timeInMillis - calendar.timeInMillis > 0
        ) {
            calendar.add(Calendar.DATE, 1)
        }

        alarmManager.setAlarmClock(
            AlarmManager.AlarmClockInfo(calendar.timeInMillis, newIntent),
            newIntent
        )
    }

    fun stopReminder(
        context: Context,
        intent: PendingIntent,
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(intent)
    }

    companion object {

        const val REMINDER_NOTIFICATION_REQUEST_CODE = 123

    }

}