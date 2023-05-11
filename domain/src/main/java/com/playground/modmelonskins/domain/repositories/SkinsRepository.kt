package com.playground.modmelonskins.domain.repositories

import com.playground.modmelonskins.domain.entities.SkinEntity

interface SkinsRepository {

    suspend fun getSkinsList(): List<SkinEntity>

    fun getItemSkin(id: Int): SkinEntity?

}