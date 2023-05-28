package com.albro.storyapp.core.data.source.remote.network

import com.albro.storyapp.core.data.source.remote.responses.LoginResponse
import com.albro.storyapp.core.data.source.remote.responses.PostStoryResponse
import com.albro.storyapp.core.data.source.remote.responses.RegisterResponse
import com.albro.storyapp.core.data.source.remote.responses.StoriesResponse
import com.albro.storyapp.core.data.source.remote.responses.StoryResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("login")
    suspend fun login(
        @Body requestBody: Map<String, String>,
    ): Response<LoginResponse>

    @POST("register")
    suspend fun register(
        @Body requestBody: Map<String, String>,
    ): Response<RegisterResponse>

    @POST("stories")
    suspend fun uploadStory(
        @Header("Authorization") token: String,
        @Body requestBody: RequestBody
    ): Response<PostStoryResponse>

    @GET("stories")
    suspend fun getStories(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("location") location: Int = 0
    ): Response<StoriesResponse>

    @GET("stories/{id}")
    suspend fun getStory(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    ): Response<StoryResponse>
}