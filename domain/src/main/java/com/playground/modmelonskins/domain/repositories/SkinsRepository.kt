package com.playground.modmelonskins.domain.repositories

import com.playground.modmelonskins.domain.entities.SkinEntity

interface SkinsRepository {

    suspend fun getSkinsList(): List<SkinEntity>

    suspend fun getItemSkin(id: Int): SkinEntity

    suspend fun downloadSkin(path: String): Boolean

}