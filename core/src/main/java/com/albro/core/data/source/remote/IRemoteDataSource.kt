package com.albro.core.data.source.remote

import com.albro.core.data.source.remote.responses.LoginResponse
import com.albro.core.data.source.remote.responses.PostStoryResponse
import com.albro.core.data.source.remote.responses.RegisterResponse
import com.albro.core.utils.ApiResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

interface IRemoteDataSource {
    fun login(requestBody: HashMap<String, String>): Flow<ApiResponse<LoginResponse?>>
    fun register(requestBody: HashMap<String, String>): Flow<ApiResponse<RegisterResponse?>>
    fun postStory(token: String, requestBody: RequestBody): Flow<ApiResponse<PostStoryResponse?>>
}