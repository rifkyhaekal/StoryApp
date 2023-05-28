package com.albro.storyapp.add_story.di

import com.albro.storyapp.core.domain.usecases.story.PostStoryInteractor
import com.albro.storyapp.core.domain.usecases.story.interfaces.PostStoryUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AddStoryModule {
    @Binds
    @Singleton
    abstract fun providePostStoryUseCase(postStoryInteractor: PostStoryInteractor): PostStoryUseCase
}