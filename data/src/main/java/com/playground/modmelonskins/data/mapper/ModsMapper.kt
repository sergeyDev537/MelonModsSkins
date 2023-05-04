package com.playground.modmelonskins.data.mapper

import com.playground.modmelonskins.data.dto.ModDto
import com.playground.modmelonskins.domain.entities.ModEntity

class ModsMapper {

    fun mapListDtoToEntity(listDto: List<ModDto>) = listDto.map {
        mapDtoToEntity(it)
    }

    fun mapDtoToEntity(modDto:ModDto) = ModEntity(
        id = modDto.id,
        name = modDto.name,
        description = modDto.description,
        imagesPath = modDto.imagesPath,
        pathFile = modDto.pathFile
    )

}