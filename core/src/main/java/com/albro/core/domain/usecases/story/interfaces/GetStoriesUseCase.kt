package com.albro.core.domain.usecases.story.interfaces

import androidx.paging.PagingData
import com.albro.core.domain.models.Story
import kotlinx.coroutines.flow.Flow

interface GetStoriesUseCase {
    suspend fun invoke(token: String): Flow<PagingData<Story>>
}