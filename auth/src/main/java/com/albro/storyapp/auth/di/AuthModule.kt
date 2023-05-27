package com.albro.storyapp.auth.di

import com.albro.storyapp.core.domain.usecases.auth.LoginInteractor
import com.albro.storyapp.core.domain.usecases.auth.RegisterInteractor
import com.albro.storyapp.core.domain.usecases.auth.interfaces.LoginUseCase
import com.albro.storyapp.core.domain.usecases.auth.interfaces.RegisterUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {
    @Binds
    @Singleton
    abstract fun provideLoginUseCase(loginInteractor: LoginInteractor): LoginUseCase

    @Binds
    @Singleton
    abstract fun provideRegisterUseCase(registerInteractor: RegisterInteractor): RegisterUseCase
}