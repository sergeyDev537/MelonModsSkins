package com.playground.modmelonskins.data.mapper

import com.playground.modmelonskins.data.dto.SkinDto
import com.playground.modmelonskins.domain.entities.SkinEntity

class SkinsMapper {

    fun mapListDtoToEntity(listDto: List<SkinDto>) = listDto.map {
        mapDtoToEntity(it)
    }

    fun mapDtoToEntity(modDto: SkinDto) = SkinEntity(
        id = modDto.id,
        name = modDto.name,
        description = modDto.description,
        imagesPath = modDto.imagesPath,
        pathFile = modDto.pathFile
    )

}