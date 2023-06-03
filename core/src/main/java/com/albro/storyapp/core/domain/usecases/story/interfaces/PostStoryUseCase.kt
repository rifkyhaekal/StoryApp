package com.albro.storyapp.core.domain.usecases.story.interfaces

import com.albro.storyapp.core.domain.models.UploadStory
import com.albro.storyapp.core.utils.UiState
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostStoryUseCase {
    operator fun invoke(token: String, description: String, imgStory: File, lat: Double?, lon: Double?): Flow<UiState<UploadStory>>
}