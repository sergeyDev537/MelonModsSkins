package com.playground.modmelonskins.domain.repositories

import com.playground.modmelonskins.domain.entities.ModEntity

interface ModsRepository {

    suspend fun getModsList(): List<ModEntity>

    suspend fun getItemMod(id: Int): ModEntity

    suspend fun downloadMod(path: String): Boolean

}