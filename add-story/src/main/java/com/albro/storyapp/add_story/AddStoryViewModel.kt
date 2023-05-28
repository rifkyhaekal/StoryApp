package com.albro.storyapp.add_story

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.albro.storyapp.core.domain.models.UploadStory
import com.albro.storyapp.core.domain.usecases.auth.interfaces.GetUserTokenUseCase
import com.albro.storyapp.core.domain.usecases.story.interfaces.PostStoryUseCase
import com.albro.storyapp.core.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AddStoryViewModel @Inject constructor(private val getUserTokenUseCase: GetUserTokenUseCase, private val postStoryUseCase: PostStoryUseCase) : ViewModel() {
    var isImagePicked = MutableStateFlow(false)
    var descriptionValue = MutableStateFlow("")

    val isUploadButtonEnable = combine(
        isImagePicked,
        descriptionValue
    ) { imagePicked, descriptionInput ->
        imagePicked && descriptionInput.isNotEmpty()
    }

    fun tokenKey(): LiveData<String> = getUserTokenUseCase().asLiveData()

    fun uploadStory(
        token: String,
        description: String,
        imgStory: File,
    ): LiveData<UiState<UploadStory>> {
        return postStoryUseCase(token, description, imgStory).asLiveData()
    }
}