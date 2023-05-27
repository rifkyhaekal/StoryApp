package com.albro.storyapp.core.domain.usecases.story.interfaces

import androidx.paging.PagingData
import com.albro.storyapp.core.domain.models.Story
import kotlinx.coroutines.flow.Flow

interface GetStoriesUseCase {
    suspend fun invoke(token: String): Flow<PagingData<Story>>
}