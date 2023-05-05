package com.playground.modmelonskins.di

import com.playground.modmelonskins.data.impl.ModsRepositoryImpl
import com.playground.modmelonskins.data.impl.SkinsRepositoryImpl
import com.playground.modmelonskins.data.mapper.ModsMapper
import com.playground.modmelonskins.data.mapper.SkinsMapper
import com.playground.modmelonskins.domain.repositories.ModsRepository
import com.playground.modmelonskins.domain.repositories.SkinsRepository
import com.playground.modmelonskins.firebase.FirebaseManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    ):ModsRepository = ModsRepositoryImpl(
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