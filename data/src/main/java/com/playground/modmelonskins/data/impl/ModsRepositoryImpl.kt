package com.playground.modmelonskins.data.impl

import com.playground.modmelonskins.data.mapper.ModsMapper
import com.playground.modmelonskins.domain.entities.ModEntity
import com.playground.modmelonskins.domain.repositories.ModsRepository
import com.playground.modmelonskins.firebase.FirebaseManager


class ModsRepositoryImpl(
    private val firebaseManager: FirebaseManager,
    private val modsMapper: ModsMapper
) : ModsRepository {

    private var listMods: List<ModEntity> = listOf()

    override suspend fun getModsList(): List<ModEntity> {
        return try {
            val listModsDto = firebaseManager.getListMods()
            listMods = modsMapper.mapListDtoToEntity(listModsDto)
            listMods
        } catch (e: Exception) {
            listOf()
        }
    }

    override fun getItemMod(id: Int): ModEntity? {
        return listMods.find { mod -> id == mod.id }
    }

}