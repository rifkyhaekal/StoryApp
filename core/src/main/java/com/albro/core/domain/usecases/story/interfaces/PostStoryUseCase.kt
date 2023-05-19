package com.albro.core.domain.usecases.story.interfaces

import com.albro.core.domain.models.UploadStory
import com.albro.core.utils.UiState
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostStoryUseCase {
    operator fun invoke(token: String, description: String, imgStory: File): Flow<UiState<UploadStory>>
}