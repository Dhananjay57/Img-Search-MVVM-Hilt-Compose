package com.dhananjay.imgsearch.di

import com.dhananjay.imgsearch.data.datasource.ImgApiService
import com.dhananjay.imgsearch.data.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideImageRepository(imgApiService: ImgApiService): ImageRepository {
        return ImageRepository(imgApiService)
    }
}