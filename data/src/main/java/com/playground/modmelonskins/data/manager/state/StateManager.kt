package com.playground.modmelonskins.data.manager.state

import android.content.Context
import com.playground.modmelonskins.data.R

class StateManager(private val context: Context) {

    private val sharedPref = context.getSharedPreferences(
        context.getString(R.string.preference_file_key),
        Context.MODE_PRIVATE
    )

    fun getAfterDownloadItem(): Boolean{
        return sharedPref.getBoolean(KEY_AFTER_DOWNLOAD, DEFAULT_VALUE)
    }

    fun setAfterDownloadItem(){
        with (sharedPref.edit()) {
            putBoolean(KEY_AFTER_DOWNLOAD, true)
            apply()
        }
    }

    fun saveTime(){
        with (sharedPref.edit()) {
            putLong(KEY_TIME_DOWNLOAD, System.currentTimeMillis())
            apply()
        }
    }

    fun getSavedTime(): Long{
        return sharedPref.getLong(KEY_TIME_DOWNLOAD, DEFAULT_TIME)
    }

    fun setShowTime(){
        with (sharedPref.edit()) {
            putBoolean(KEY_SHOW_TIME_DOWNLOAD, true)
            apply()
        }
    }

    fun getShowTime(): Boolean{
        return sharedPref.getBoolean(KEY_SHOW_TIME_DOWNLOAD, DEFAULT_VALUE)
    }

    companion object{

        const val DEFAULT_VALUE = false
        const val DEFAULT_TIME = 0L
        const val KEY_AFTER_DOWNLOAD = "Key after download"
        const val KEY_TIME_DOWNLOAD = "Key time download"
        const val KEY_SHOW_TIME_DOWNLOAD = "Key show time download"
//        const val DEFAULT_24_HOURS = 24 * 60 * 60 * 1000L
        const val DEFAULT_24_HOURS = 30 * 1000L

    }

}