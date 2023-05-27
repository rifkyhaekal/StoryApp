package com.albro.storyapp.di

import com.albro.storyapp.core.domain.usecases.auth.GetLoginStateInteractor
import com.albro.storyapp.core.domain.usecases.auth.interfaces.GetLoginStateUseCase
import javax.inject.Singleton
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideGetLoginStateUseCase(getLoginStateInteractor: GetLoginStateInteractor): GetLoginStateUseCase
}
