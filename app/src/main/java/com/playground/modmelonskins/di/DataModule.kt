package com.playground.modmelonskins.di

import android.content.Context
import com.playground.modmelonskins.ads.managers.BannerManager
import com.playground.modmelonskins.ads.managers.InterstitialManager
import com.playground.modmelonskins.ads.managers.NativeManager
import com.playground.modmelonskins.data.impl.AdsRepositoryImpl
import com.playground.modmelonskins.data.impl.DownloadRepositoryImpl
import com.playground.modmelonskins.data.impl.ModsRepositoryImpl
import com.playground.modmelonskins.data.impl.SkinsRepositoryImpl
import com.playground.modmelonskins.data.manager.downloader.AndroidDownloader
import com.playground.modmelonskins.data.manager.state.StateManager
import com.playground.modmelonskins.data.mapper.ModsMapper
import com.playground.modmelonskins.data.mapper.SkinsMapper
import com.playground.modmelonskins.domain.repositories.AdsRepository
import com.playground.modmelonskins.domain.repositories.DownloadRepository
import com.playground.modmelonskins.domain.repositories.ModsRepository
import com.playground.modmelonskins.domain.repositories.SkinsRepository
import com.playground.modmelonskins.firebase.FirebaseManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    /*REPOSITORIES*/
    @Provides
    @Singleton
    fun provideModsRepository(
        firebaseManager: FirebaseManager,
        modsMapper: ModsMapper,
    ): ModsRepository = ModsRepositoryImpl(
        firebaseManager = firebaseManager,
        modsMapper = modsMapper
    )

    @Provides
    @Singleton
    fun provideSkinsRepository(
        firebaseManager: FirebaseManager,
        skinsMapper: SkinsMapper,
    ): SkinsRepository = SkinsRepositoryImpl(
        firebaseManager = firebaseManager,
        skinsMapper = skinsMapper
    )

    @Provides
    @Singleton
    fun provideDownloadRepository(
        downloader: AndroidDownloader,
    ): DownloadRepository = DownloadRepositoryImpl(
        downloader = downloader
    )

    @Provides
    @Singleton
    fun provideAdsRepository(
        nativeManager: NativeManager,
        bannerManager: BannerManager,
        interstitialManager: InterstitialManager
    ): AdsRepository = AdsRepositoryImpl(
        nativeManager = nativeManager,
        bannerManager = bannerManager,
        interstitialManager = interstitialManager
    )

    /*ADS MANAGERS*/
    @Provides
    @Singleton
    fun provideNativeManager(@ApplicationContext context: Context) =
        NativeManager(context = context)

    @Provides
    @Singleton
    fun provideBannerManager() = BannerManager()

    @Provides
    @Singleton
    fun provideInterstitialManager(@ApplicationContext context: Context) =
        InterstitialManager(context)


    /*DOWNLOADER*/
    @Provides
    @Singleton
    fun provideDownloader(@ApplicationContext context: Context, firebaseManager: FirebaseManager) =
        AndroidDownloader(context = context, firebaseManager = firebaseManager)

    /*STATE MANAGER*/
    @Provides
    @Singleton
    fun provideStateManager(@ApplicationContext context: Context) =
        StateManager(context)

    /*MAPPERS*/
    @Provides
    @Singleton
    fun provideModsMapper() = ModsMapper()

    @Provides
    @Singleton
    fun provideSkinsMapper() = SkinsMapper()

    /*FIREBASE*/
    @Provides
    @Singleton
    fun provideFirebaseManager() = FirebaseManager()

}