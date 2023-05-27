package com.albro.storyapp.core.domain.usecases.story

import androidx.paging.PagingData
import com.albro.storyapp.core.data.repositories.StoryRepository
import com.albro.storyapp.core.domain.models.Story
import com.albro.storyapp.core.domain.usecases.story.interfaces.GetStoriesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoriesInteractor @Inject constructor(private val storyRepository: StoryRepository) :
    GetStoriesUseCase {
    override suspend fun invoke(token: String): Flow<PagingData<Story>> =
        storyRepository.getStories(token)
}