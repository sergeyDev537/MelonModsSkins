package com.playground.modmelonskins.data.mapper

import com.playground.modmelonskins.domain.entities.ModEntity
import com.playground.modmelonskins.firebase.dto.ModDto

class ModsMapper {

    fun mapListDtoToEntity(listDto: List<ModDto?>) = listDto.map {
        mapDtoToEntity(it)
    }

    private fun mapDtoToEntity(modDto:ModDto?) = ModEntity(
        id = modDto?.id,
        name = modDto?.name,
        description = modDto?.description,
        imagesPath = modDto?.images,
        pathFile = modDto?.pathFile
    )

}