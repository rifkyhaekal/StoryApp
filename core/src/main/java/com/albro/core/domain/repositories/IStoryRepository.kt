package com.albro.core.domain.repositories

import androidx.paging.PagingData
import com.albro.core.domain.models.Story
import com.albro.core.domain.models.UploadStory
import com.albro.core.utils.ApiResponse
import com.albro.core.utils.UiState
import kotlinx.coroutines.flow.Flow
import java.io.File

interface IStoryRepository {
    fun getStories(
        token: String,
    ): Flow<PagingData<Story>>

    fun postStory(
        token: String,
        description: String,
        imgStory: File,
    ): Flow<UiState<UploadStory>>
}