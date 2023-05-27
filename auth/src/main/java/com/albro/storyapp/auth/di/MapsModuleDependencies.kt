package com.albro.storyapp.auth.di

import com.albro.storyapp.core.domain.usecases.auth.interfaces.LoginUseCase
import com.albro.storyapp.core.domain.usecases.auth.interfaces.RegisterUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface MapsModuleDependencies {
    fun loginUseCase(): LoginUseCase
    fun registerUseCase(): RegisterUseCase
}