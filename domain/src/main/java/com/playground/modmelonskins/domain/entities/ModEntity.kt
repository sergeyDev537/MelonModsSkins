package com.playground.modmelonskins.domain.entities

data class ModEntity(
    val id: Int,
    val name: String,
    val description: String,
    val imagesPath: List<String>,
    val pathFile: String
)