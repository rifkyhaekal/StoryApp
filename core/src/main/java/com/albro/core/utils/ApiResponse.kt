package com.albro.core.utils

sealed class ApiResponse<T>(data: T? = null, errorMessage: String?) {
    data class Success<T>(val data: T) : ApiResponse<T>(data, null)
    data class Error<T>(val errorMessage: String) : ApiResponse<T>(null, errorMessage)
}