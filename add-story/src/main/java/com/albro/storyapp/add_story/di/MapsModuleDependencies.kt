package com.albro.storyapp.add_story.di

import com.albro.storyapp.core.domain.usecases.story.interfaces.PostStoryUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface MapsModuleDependencies {
    fun postStoryUseCase(): PostStoryUseCase
}