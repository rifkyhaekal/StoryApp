package com.albro.storyapp.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.albro.storyapp.core.data.source.local.preferences.AppDataStore
import com.albro.storyapp.core.data.source.local.preferences.DataStoreDataSource
import com.albro.storyapp.core.data.source.local.preferences.IDataStoreDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {
    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.AppDataStore
    }

    @Singleton
    @Provides
    fun provideDataStoreDataSource(dataStore: DataStore<Preferences>): IDataStoreDataSource {
        return DataStoreDataSource(dataStore)
    }
}