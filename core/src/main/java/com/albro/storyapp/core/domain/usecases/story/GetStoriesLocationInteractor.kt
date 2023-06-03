package com.albro.storyapp.core.domain.usecases.story

import com.albro.storyapp.core.data.repositories.StoryRepository
import com.albro.storyapp.core.domain.models.Story
import com.albro.storyapp.core.domain.usecases.story.interfaces.GetStoriesLocationUseCase
import com.albro.storyapp.core.utils.UiState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoriesLocationInteractor @Inject constructor(private val storyRepository: StoryRepository) : GetStoriesLocationUseCase {
    override fun invoke(token: String): Flow<UiState<ArrayList<Story>>> =
        storyRepository.getAllWithStories(token)
}