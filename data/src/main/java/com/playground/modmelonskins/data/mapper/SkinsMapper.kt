package com.playground.modmelonskins.data.mapper

import com.playground.modmelonskins.domain.entities.SkinEntity
import com.playground.modmelonskins.firebase.dto.SkinDto

class SkinsMapper {

    fun mapListDtoToEntity(listDto: List<SkinDto?>) = listDto.map {
        mapDtoToEntity(it)
    }

    private fun mapDtoToEntity(modDto: SkinDto?) = SkinEntity(
        id = modDto?.id,
        name = modDto?.name,
        description = modDto?.description,
        imagesPath = modDto?.images,
        pathFile = modDto?.pathFile
    )

}