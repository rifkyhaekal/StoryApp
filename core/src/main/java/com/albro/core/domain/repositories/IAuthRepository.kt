package com.albro.core.domain.repositories

import com.albro.core.domain.models.Login
import com.albro.core.domain.models.Register
import com.albro.core.utils.ApiResponse
import com.albro.core.utils.UiState
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    suspend fun setLoginState(state: Boolean)
    suspend fun setTokenKey(tokenKey: String)
    suspend fun deleteTokenKey()
    fun getLoginState(): Flow<Boolean>
    fun getTokenKey(): Flow<String>
    suspend fun login(requestBody: HashMap<String, String>): Flow<UiState<Login>>
    suspend fun register(requestBody: HashMap<String, String>): Flow<UiState<Register>>
}