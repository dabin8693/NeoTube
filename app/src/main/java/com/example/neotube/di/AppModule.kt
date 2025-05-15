package com.example.neotube.di

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import com.example.neotube.model.video.VideoRepository
import com.example.neotube.model.video.VideoRepositoryImpl
import com.example.neotube.usecase.GetVideoListUseCase
import com.example.neotube.videoplayer.ExoPlayerControllerTest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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


    @Provides
    fun provideExoPlayer(
        @ApplicationContext context: Context
    ): ExoPlayer {
        return ExoPlayer.Builder(context).build()
    }
    @Provides
    fun provideExoPlayerControllerTest(exoPlayer: ExoPlayer): ExoPlayerControllerTest {
        return ExoPlayerControllerTest(exoPlayer)
    }


}