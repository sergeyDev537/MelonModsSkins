package com.playground.modmelonskins.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.playground.modmelonskins.data.manager.daily.RemindersManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class BootReceiver : BroadcastReceiver() {

    lateinit var remindersManager: RemindersManager

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            remindersManager = RemindersManager(context)
            remindersManager.startReminder(
                intent = Intent(context.applicationContext, AlarmReceiver::class.java)
            )
        }
    }
}