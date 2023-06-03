package com.albro.storyapp.map_story.di

import com.albro.storyapp.core.domain.usecases.story.interfaces.GetStoriesLocationUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface MapsModuleDependencies {
    fun getStoriesLocationUseCase(): GetStoriesLocationUseCase
}