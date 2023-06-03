package com.albro.storyapp.core.di

import com.albro.storyapp.core.data.repositories.AuthRepository
import com.albro.storyapp.core.data.repositories.StoryRepository
import com.albro.storyapp.core.domain.repositories.IAuthRepository
import com.albro.storyapp.core.domain.repositories.IStoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Binds
    abstract fun provideAuthRepository(authRepository: AuthRepository): IAuthRepository

    @Binds
    abstract fun provideStoryRepository(storyRepository: StoryRepository): IStoryRepository
}