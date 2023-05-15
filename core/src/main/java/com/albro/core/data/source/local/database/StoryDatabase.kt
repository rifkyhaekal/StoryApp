package com.albro.core.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.albro.core.domain.models.Story

@Database(entities = [Story::class, RemoteKey::class], version = 1, exportSchema = false)
abstract class StoryDatabase: RoomDatabase() {
    abstract fun storyDao(): StoryDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}