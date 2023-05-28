package com.albro.storyapp.di

import com.albro.storyapp.core.domain.usecases.auth.interfaces.GetLoginStateUseCase
import com.albro.storyapp.core.domain.usecases.auth.interfaces.GetUserTokenUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface MapsModuleDependencies {
    fun getLoginStateUseCase(): GetLoginStateUseCase
    fun getUserTokenUseCase(): GetUserTokenUseCase
}