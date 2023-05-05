package com.playground.modmelonskins.di

import com.playground.modmelonskins.domain.repositories.InfoRepository
import com.playground.modmelonskins.domain.repositories.ModsRepository
import com.playground.modmelonskins.domain.repositories.SkinsRepository
import com.playground.modmelonskins.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetListModsUseCase(modsRepository: ModsRepository) =
        GetListModsUseCase(modsRepository)

    @Provides
    fun provideGetListSkinsUseCase(skinsRepository: SkinsRepository) =
        GetListSkinsUseCase(skinsRepository)

    @Provides
    fun provideDownloadModsUseCase(modsRepository: ModsRepository) =
        DownloadModsUseCase(modsRepository)

    @Provides
    fun provideDownloadSkinsUseCase(skinsRepository: SkinsRepository) =
        DownloadSkinsUseCase(skinsRepository)

    @Provides
    fun provideGetInfoUseCase(infoRepository: InfoRepository) =
        GetInfoUseCase(infoRepository)

    @Provides
    fun provideGetItemModUseCase(modsRepository: ModsRepository) =
        GetItemModUseCase(modsRepository)

    @Provides
    fun provideGetItemSkinUseCase(skinsRepository: SkinsRepository) =
        GetItemSkinUseCase(skinsRepository)

}