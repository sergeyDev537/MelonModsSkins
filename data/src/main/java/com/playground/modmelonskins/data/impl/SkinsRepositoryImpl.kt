package com.playground.modmelonskins.data.impl

import com.playground.modmelonskins.data.mapper.SkinsMapper
import com.playground.modmelonskins.domain.entities.SkinEntity
import com.playground.modmelonskins.domain.repositories.SkinsRepository
import com.playground.modmelonskins.firebase.FirebaseManager

class SkinsRepositoryImpl(
    private val firebaseManager: FirebaseManager,
    private val skinsMapper: SkinsMapper,
) : SkinsRepository {

    private var listSkins: List<SkinEntity> = listOf()

    override suspend fun getSkinsList(): List<SkinEntity> {
        return try {
            val listSkinsDto = firebaseManager.getListSkins()
            listSkins = skinsMapper.mapListDtoToEntity(listSkinsDto)
            listSkins
        } catch (e: Exception) {
            listOf()
        }
    }

    override fun getItemSkin(id: Int): SkinEntity? {
        return listSkins.find { mod -> id == mod.id }
    }
}