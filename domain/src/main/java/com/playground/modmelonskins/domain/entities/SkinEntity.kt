package com.playground.modmelonskins.domain.entities

data class SkinEntity(
    val id: Int?,
    val name: String?,
    val description: String?,
    val imagesPath: List<String>?,
    val pathFile: String?
)