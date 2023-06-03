package com.albro.storyapp.core.domain.repositories

import androidx.paging.PagingData
import com.albro.storyapp.core.data.source.remote.network.ApiResponse
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
        lat: Double? = null,
        lon: Double? = null,
    ): Flow<UiState<UploadStory>>

    fun getStories(
        token: String,
    ): Flow<PagingData<Story>>

    fun getAllWithStories(
        token: String,
    ): Flow<UiState<ArrayList<Story>>>
}