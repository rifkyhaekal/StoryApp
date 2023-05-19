package com.albro.core.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.albro.core.data.mediator.StoryRemoteMediator
import com.albro.core.data.source.local.room.StoryDatabase
import com.albro.core.data.source.remote.IRemoteDataSource
import com.albro.core.data.source.remote.network.ApiService
import com.albro.core.domain.models.Story
import com.albro.core.domain.models.UploadStory
import com.albro.core.domain.repositories.IStoryRepository
import com.albro.core.utils.ApiResponse
import com.albro.core.utils.UiState
import com.albro.core.utils.mapToDomain
import com.albro.core.utils.toMultipartBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import okhttp3.MultipartBody
import java.io.File

class StoryRepository(
    private val storyDatabase: StoryDatabase,
    private val remoteDataSource: IRemoteDataSource,
    private val apiService: ApiService
) : IStoryRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getStories(token: String): Flow<PagingData<Story>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            remoteMediator = StoryRemoteMediator(storyDatabase, apiService, "Bearer $token"),
            pagingSourceFactory = {
                storyDatabase.storyDao().getStories()
            }
        ).flow.map { pagingData ->
            pagingData.map { storyEntity ->
                storyEntity.mapToDomain()
            }
        }
    }

    override fun postStory(
        token: String,
        description: String,
        imgStory: File,
    ): Flow<UiState<UploadStory>> {
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("description", description)
            .addPart(imgStory.toMultipartBody("photo"))
            .build()

        return flow {
            remoteDataSource.postStory(token, requestBody).onStart {
                emit(UiState.loading())
            }.collect { response ->
                emit(UiState.hideLoading())
                when (response) {
                    is ApiResponse.Success -> {
                        val uploadStory = response.data?.mapToDomain()
                        if (uploadStory != null) {
                            emit(UiState.success(uploadStory))
                        } else {
                            emit(UiState.error(message = "Error converting response."))
                        }
                    }

                    is ApiResponse.Error -> emit(UiState.error(message = response.errorMessage))
                }
            }
        }
    }


    private companion object {
        const val NETWORK_PAGE_SIZE = 5
    }
}