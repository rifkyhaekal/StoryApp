package com.albro.storyapp.core.data.source.local.room.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.albro.storyapp.core.data.source.local.entity.StoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStories(quote: List<StoryEntity>)

    @Query("DELETE FROM stories")
    suspend fun deleteStories()

    @Query("SELECT * FROM stories")
    fun getStories(): PagingSource<Int, StoryEntity>
}