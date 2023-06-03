package com.albro.storyapp.map_story

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.albro.storyapp.core.domain.models.Story
import com.albro.storyapp.core.domain.usecases.auth.interfaces.GetUserTokenUseCase
import com.albro.storyapp.core.domain.usecases.story.interfaces.GetStoriesLocationUseCase
import com.albro.storyapp.core.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapStoryViewModel @Inject constructor(
    private val getStoriesLocationUseCase: GetStoriesLocationUseCase,
    private val getUserTokenUseCase: GetUserTokenUseCase
) : ViewModel() {

    fun getStoriesWithLocation(token: String): LiveData<UiState<ArrayList<Story>>> {
        return getStoriesLocationUseCase(token).asLiveData()
    }

    fun tokenKey(): LiveData<String> = getUserTokenUseCase().asLiveData()
}