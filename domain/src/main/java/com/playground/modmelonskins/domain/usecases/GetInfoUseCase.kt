package com.playground.modmelonskins.domain.usecases

import com.playground.modmelonskins.domain.repositories.InfoRepository

class GetInfoUseCase(private val infoRepository: InfoRepository) {

    operator fun invoke(): String{
        return infoRepository.getInfo()
    }

}