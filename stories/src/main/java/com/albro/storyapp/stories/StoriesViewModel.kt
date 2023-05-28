package com.albro.storyapp.stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.albro.storyapp.core.domain.models.Story
import com.albro.storyapp.core.domain.usecases.auth.interfaces.GetUserTokenUseCase
import com.albro.storyapp.core.domain.usecases.story.interfaces.GetStoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoriesViewModel @Inject constructor(private val getStoriesUseCase: GetStoriesUseCase, private val getUserTokenUseCase: GetUserTokenUseCase) : ViewModel() {

    suspend fun getStories(token: String): LiveData<PagingData<Story>> =
        getStoriesUseCase(token).cachedIn(viewModelScope).asLiveData()

    fun tokenKey(): LiveData<String> = getUserTokenUseCase().asLiveData()
}