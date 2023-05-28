package com.albro.storyapp.profile.di

import com.albro.storyapp.core.domain.usecases.auth.LogoutInteractor
import com.albro.storyapp.core.domain.usecases.auth.interfaces.LogoutUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileModule {
    @Binds
    @Singleton
    abstract fun provideLogoutUseCase(logoutInteractor: LogoutInteractor): LogoutUseCase
}