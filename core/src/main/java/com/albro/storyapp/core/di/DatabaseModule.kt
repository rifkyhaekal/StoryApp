package com.albro.storyapp.core.di

import android.content.Context
import androidx.room.Room
import com.albro.storyapp.core.data.source.local.room.StoryDatabase
import com.albro.storyapp.core.data.source.local.room.daos.StoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): StoryDatabase = Room.databaseBuilder(
        context,
        StoryDatabase::class.java, "story.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideStoryDao(database: StoryDatabase): StoryDao = database.storyDao()
}