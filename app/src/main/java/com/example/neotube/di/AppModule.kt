package com.example.neotube.di

import com.example.neotube.model.video.VideoRepository
import com.example.neotube.model.video.VideoRepositoryImpl
import com.example.neotube.usecase.GetVideoListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGetVideoListUseCase(repository: VideoRepository): GetVideoListUseCase {
        return GetVideoListUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideVideoRepository(): VideoRepository {
        return VideoRepositoryImpl()
    }

}