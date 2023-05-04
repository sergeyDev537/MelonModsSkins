package com.playground.modmelonskins.data.impl

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.playground.modmelonskins.data.dto.ModDto
import com.playground.modmelonskins.data.mapper.ModsMapper
import com.playground.modmelonskins.domain.entities.ModEntity
import com.playground.modmelonskins.domain.repositories.ModsRepository
import com.playground.modmelonskins.firebase.FirebaseManager

class ModsRepositoryImpl(
    private val firebaseManager: FirebaseManager,
    private val modsMapper: ModsMapper,
) : ModsRepository {

    private var listMods: List<ModEntity> = listOf()

    override suspend fun getModsList(): List<ModEntity> {
        return try {
            val json = firebaseManager.getJson(FirebaseManager.FILE_MODS_JSON)
            val typeToken = object : TypeToken<List<ModDto>>() {}.type
            val listModsDto = Gson().fromJson<List<ModDto>>(json, typeToken)
            listMods = modsMapper.mapListDtoToEntity(listModsDto)
            listMods
        } catch (e: Exception) {
            listOf()
        }
    }

    override fun getItemMod(id: Int): ModEntity? {
        return listMods.find { mod -> id == mod.id }
    }

    override suspend fun downloadMod(path: String): Boolean {
        TODO("Not yet implemented")
    }

}