package com.playground.modmelonskins.firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.playground.modmelonskins.firebase.dto.ModDto
import com.playground.modmelonskins.firebase.dto.SkinDto
import kotlinx.coroutines.tasks.await

class FirebaseManager {

    private val database = Firebase.database

    private suspend fun getDataSnapshot(typeMods: String): DataSnapshot {
        val myRef = database.getReference(typeMods)
        val result = myRef.get().await()
        return result
    }

    suspend fun getListMods():List<ModDto?>{
        val dataSnapshot = getDataSnapshot(FILE_MODS_JSON)
        val listDto = mutableListOf<ModDto?>()
        for (postSnapshot in dataSnapshot.children){
            val itemDto = postSnapshot.getValue(ModDto::class.java)
            listDto.add(itemDto)
        }
        return listDto
    }

    suspend fun getListSkins():List<SkinDto?>{
        val dataSnapshot = getDataSnapshot(FILE_SKINS_JSON)
        val listDto = mutableListOf<SkinDto?>()
        for (postSnapshot in dataSnapshot.children){
            val itemDto = postSnapshot.getValue(SkinDto::class.java)
            listDto.add(itemDto)
        }
        return listDto
    }

    fun getNameFile(url: String): String {
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(url)
        return storageReference.name
    }

    companion object{

        const val FILE_MODS_JSON = "mods"
        const val FILE_SKINS_JSON = "skins"
        const val EMPTY_STRING = ""

    }

}