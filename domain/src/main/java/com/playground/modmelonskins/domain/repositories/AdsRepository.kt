package com.playground.modmelonskins.domain.repositories

import android.view.ViewGroup

interface AdsRepository {

    fun loadNative(viewGroup: ViewGroup)

    fun loadBanner(viewGroup: ViewGroup)

}