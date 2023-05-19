package com.albro.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.albro.core.data.source.local.entity.RemoteKeyEntity
import com.albro.core.data.source.local.entity.StoryEntity
import com.albro.core.data.source.local.room.daos.RemoteKeyDao
import com.albro.core.data.source.local.room.daos.StoryDao

@Database(entities = [StoryEntity::class, RemoteKeyEntity::class], version = 1, exportSchema = false)
abstract class StoryDatabase: RoomDatabase() {
    abstract fun storyDao(): StoryDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}