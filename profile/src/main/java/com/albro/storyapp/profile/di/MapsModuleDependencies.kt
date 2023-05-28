package com.albro.storyapp.profile.di

import com.albro.storyapp.core.domain.usecases.auth.interfaces.LogoutUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface MapsModuleDependencies {
    fun logoutUseCase(): LogoutUseCase
}