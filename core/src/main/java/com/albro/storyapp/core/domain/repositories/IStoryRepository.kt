package com.albro.storyapp.core.domain.repositories

import androidx.paging.PagingData
import com.albro.storyapp.core.domain.models.Story
import com.albro.storyapp.core.domain.models.UploadStory
import com.albro.storyapp.core.utils.UiState
import kotlinx.coroutines.flow.Flow
import java.io.File

interface IStoryRepository {

    fun postStory(
        token: String,
        description: String,
        imgStory: File,
    ): Flow<UiState<UploadStory>>

    fun getStories(
        token: String,
    ): Flow<PagingData<Story>>
}