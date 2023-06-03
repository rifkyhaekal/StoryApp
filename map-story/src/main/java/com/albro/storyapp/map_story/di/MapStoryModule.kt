package com.albro.storyapp.map_story.di

import com.albro.storyapp.core.domain.usecases.story.GetStoriesLocationInteractor
import com.albro.storyapp.core.domain.usecases.story.interfaces.GetStoriesLocationUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapStoryModule {
    @Binds
    @Singleton
    abstract fun provideGetStoriesLocationUseCase(getStoriesLocationInteractor: GetStoriesLocationInteractor): GetStoriesLocationUseCase
}