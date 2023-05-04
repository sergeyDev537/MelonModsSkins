package com.playground.modmelonskins.data.impl

import com.playground.modmelonskins.domain.entities.ModEntity
import com.playground.modmelonskins.domain.repositories.ModsRepository

class ModsRepositoryImpl : ModsRepository {

    override suspend fun getModsList(): List<ModEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getItemMod(id: Int): ModEntity {
        TODO("Not yet implemented")
    }

    override suspend fun downloadMod(path: String): Boolean {
        TODO("Not yet implemented")
    }

}