package com.albro.storyapp.stories.di

import com.albro.storyapp.core.domain.usecases.story.GetStoriesInteractor
import com.albro.storyapp.core.domain.usecases.story.interfaces.GetStoriesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StoriesModule {
    @Binds
    @Singleton
    abstract fun provideGetStoriesUseCase(getStoriesInteractor: GetStoriesInteractor): GetStoriesUseCase
}
