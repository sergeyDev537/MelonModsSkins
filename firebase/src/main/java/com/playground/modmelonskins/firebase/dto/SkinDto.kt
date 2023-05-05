package com.playground.modmelonskins.firebase.dto

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class SkinDto(
    val id: Int,
    val name: String,
    val description: String,
    val images: List<String>,
    val pathFile: String
){
    constructor() : this(-1, "", "", listOf(), "") {}
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "name" to name,
            "description" to description,
            "images" to images,
            "pathFile" to pathFile
        )
    }
}