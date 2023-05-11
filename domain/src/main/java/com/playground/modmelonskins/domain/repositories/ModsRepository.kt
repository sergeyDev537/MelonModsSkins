package com.playground.modmelonskins.domain.repositories

import com.playground.modmelonskins.domain.entities.ModEntity

interface ModsRepository {

    suspend fun getModsList(): List<ModEntity>

    fun getItemMod(id: Int): ModEntity?

}