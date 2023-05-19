package com.albro.core.domain.usecases.story

import androidx.paging.PagingData
import com.albro.core.data.repositories.StoryRepository
import com.albro.core.domain.models.Story
import com.albro.core.domain.usecases.story.interfaces.GetStoriesUseCase
import kotlinx.coroutines.flow.Flow

class GetStoriesInteractor(private val storyRepository: StoryRepository) : GetStoriesUseCase {
    override suspend fun invoke(token: String): Flow<PagingData<Story>> =
        storyRepository.getStories(token)
}