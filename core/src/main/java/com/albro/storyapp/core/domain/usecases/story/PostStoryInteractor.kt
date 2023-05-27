package com.albro.storyapp.core.domain.usecases.story

import com.albro.storyapp.core.data.repositories.StoryRepository
import com.albro.storyapp.core.domain.models.UploadStory
import com.albro.storyapp.core.domain.usecases.story.interfaces.PostStoryUseCase
import com.albro.storyapp.core.utils.UiState
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class PostStoryInteractor @Inject constructor(private val storyRepository: StoryRepository) :
    PostStoryUseCase {
    override fun invoke(
        token: String,
        description: String,
        imgStory: File
    ): Flow<UiState<UploadStory>> = storyRepository.postStory(token, description, imgStory)
}