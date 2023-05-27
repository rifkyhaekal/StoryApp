package com.albro.storyapp.core.data.source.remote.network

sealed class ApiResponse<T>(data: T? = null, errorMessage: String?) {
    data class Success<T>(val data: T) : ApiResponse<T>(data, null)
    data class Error<T>(val errorMessage: String) : ApiResponse<T>(null, errorMessage)
}