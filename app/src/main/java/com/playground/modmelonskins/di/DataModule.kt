package com.playground.modmelonskins.di

import android.content.Context
import com.playground.modmelonskins.data.impl.DownloadRepositoryImpl
import com.playground.modmelonskins.data.impl.ModsRepositoryImpl
import com.playground.modmelonskins.data.impl.SkinsRepositoryImpl
import com.playground.modmelonskins.data.manager.downloader.AndroidDownloader
import com.playground.modmelonskins.data.mapper.ModsMapper
import com.playground.modmelonskins.data.mapper.SkinsMapper
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

    /*DOWNLOADER*/
    @Provides
    @Singleton
    fun provideDownloader(@ApplicationContext context: Context, firebaseManager: FirebaseManager) =
        AndroidDownloader(context = context, firebaseManager = firebaseManager)


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