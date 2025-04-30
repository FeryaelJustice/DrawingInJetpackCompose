package com.feryaeljustice.drawinginjetpackcompose.di

import com.feryaeljustice.drawinginjetpackcompose.data.repository.DrawingRepositoryImpl
import com.feryaeljustice.drawinginjetpackcompose.domain.repository.DrawingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDrawingRepository(drawingRepositoryImpl: DrawingRepositoryImpl): DrawingRepository
}