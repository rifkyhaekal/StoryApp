package com.albro.storyapp.core.domain.usecases.story.interfaces

import com.albro.storyapp.core.domain.models.Story
import com.albro.storyapp.core.utils.UiState
import kotlinx.coroutines.flow.Flow

interface GetStoriesLocationUseCase {
    operator fun invoke(token: String): Flow<UiState<ArrayList<Story>>>
}