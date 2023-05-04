package com.playground.modmelonskins.data.impl

import com.playground.modmelonskins.domain.entities.SkinEntity
import com.playground.modmelonskins.domain.repositories.SkinsRepository

class SkinsRepositoryImpl : SkinsRepository {

    override suspend fun getSkinsList(): List<SkinEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getItemSkin(id: Int): SkinEntity {
        TODO("Not yet implemented")
    }

    override suspend fun downloadSkin(path: String): Boolean {
        TODO("Not yet implemented")
    }
}