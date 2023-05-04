package com.playground.modmelonskins.data.dto

data class ModDto(
    val id: Int,
    val name: String,
    val description: String,
    val imagesPath: List<String>,
    val pathFile: String
)