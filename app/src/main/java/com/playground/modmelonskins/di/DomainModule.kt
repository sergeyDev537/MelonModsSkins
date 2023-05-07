package com.playground.modmelonskins.di

import com.playground.modmelonskins.domain.repositories.*
import com.playground.modmelonskins.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

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
    fun provideDownloadItemUseCase(downloadRepository: DownloadRepository) =
        DownloadItemUseCase(downloadRepository)

    @Provides
    fun provideDownloadStatusUseCase(downloadRepository: DownloadRepository) =
        DownloadStatusUseCase(downloadRepository)

    @Provides
    fun provideGetInfoUseCase(infoRepository: InfoRepository) =
        GetInfoUseCase(infoRepository)

    @Provides
    fun provideGetItemModUseCase(modsRepository: ModsRepository) =
        GetItemModUseCase(modsRepository)

    @Provides
    fun provideGetItemSkinUseCase(skinsRepository: SkinsRepository) =
        GetItemSkinUseCase(skinsRepository)

    @Provides
    fun provideLoadNativeAdsUseCase(adsRepository: AdsRepository) =
        LoadNativeAdsUseCase(adsRepository)

    @Provides
    fun provideLoadBannerAdsUseCase(adsRepository: AdsRepository) =
        LoadBannerAdsUseCase(adsRepository)

}