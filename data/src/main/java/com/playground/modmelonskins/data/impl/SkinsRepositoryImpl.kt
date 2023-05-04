package com.playground.modmelonskins.data.impl

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.playground.modmelonskins.data.dto.ModDto
import com.playground.modmelonskins.data.dto.SkinDto
import com.playground.modmelonskins.data.mapper.ModsMapper
import com.playground.modmelonskins.data.mapper.SkinsMapper
import com.playground.modmelonskins.domain.entities.ModEntity
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
            val json = firebaseManager.getJson(FirebaseManager.FILE_SKINS_JSON)
            val typeToken = object : TypeToken<List<SkinDto>>() {}.type
            val listSkinsDto = Gson().fromJson<List<SkinDto>>(json, typeToken)
            listSkins = skinsMapper.mapListDtoToEntity(listSkinsDto)
            listSkins
        } catch (e: Exception) {
            listOf()
        }
    }

    override fun getItemSkin(id: Int): SkinEntity? {
        return listSkins.find { mod -> id == mod.id }
    }

    override suspend fun downloadSkin(path: String): Boolean {
        TODO("Not yet implemented")
    }
}