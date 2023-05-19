package com.albro.core.domain.usecases.story

import com.albro.core.data.repositories.StoryRepository
import com.albro.core.domain.models.UploadStory
import com.albro.core.domain.usecases.story.interfaces.PostStoryUseCase
import com.albro.core.utils.UiState
import kotlinx.coroutines.flow.Flow
import java.io.File

class PostStoryInteractor(private val storyRepository: StoryRepository) : PostStoryUseCase {
    override fun invoke(
        token: String,
        description: String,
        imgStory: File
    ): Flow<UiState<UploadStory>> = storyRepository.postStory(token, description, imgStory)
}