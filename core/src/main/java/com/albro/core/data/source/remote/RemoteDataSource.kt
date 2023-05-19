package com.albro.core.data.source.remote

import com.albro.core.data.source.remote.network.ApiService
import com.albro.core.data.source.remote.responses.LoginResponse
import com.albro.core.data.source.remote.responses.PostStoryResponse
import com.albro.core.data.source.remote.responses.RegisterResponse
import com.albro.core.utils.ApiResponse
import com.albro.core.utils.getErrorMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.RequestBody
import retrofit2.HttpException
import java.io.IOException

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun login(requestBody: HashMap<String, String>): Flow<ApiResponse<LoginResponse?>> =
        flow {
            try {
                val response = apiService.login(requestBody)
                if (response.isSuccessful) {
                    emit(ApiResponse.Success(response.body()))
                } else {
                    emit(ApiResponse.Error(response.getErrorMessage()))
                }
            } catch (e: HttpException) {
                emit(ApiResponse.Error(e.localizedMessage as String))
            } catch (e: IOException) {
                emit(ApiResponse.Error(e.localizedMessage as String))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun register(requestBody: HashMap<String, String>): Flow<ApiResponse<RegisterResponse?>> =
        flow {
            try {
                val response = apiService.register(requestBody)
                if (response.isSuccessful) {
                    emit(ApiResponse.Success(response.body()))
                } else {
                    emit(ApiResponse.Error(response.getErrorMessage()))
                }
            } catch (e: HttpException) {
                emit(ApiResponse.Error(e.localizedMessage as String))
            } catch (e: IOException) {
                emit(ApiResponse.Error(e.localizedMessage as String))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun postStory(
        token: String,
        requestBody: RequestBody
    ): Flow<ApiResponse<PostStoryResponse?>> =
        flow {
            try {
                val response = apiService.uploadStory(token, requestBody)
                if (response.isSuccessful) {
                    emit(ApiResponse.Success(response.body()))
                } else {
                    emit(ApiResponse.Error(response.getErrorMessage()))
                }
            } catch (e: HttpException) {
                emit(ApiResponse.Error(e.localizedMessage as String))
            } catch (e: IOException) {
                emit(ApiResponse.Error(e.localizedMessage as String))
            }
        }.flowOn(Dispatchers.IO)
}