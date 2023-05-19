package com.albro.core.domain.usecases.auth.interfaces

import com.albro.core.domain.models.Login
import com.albro.core.utils.UiState
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    suspend fun execute(requestBody: HashMap<String, String>): Flow<UiState<Login>>
    suspend fun setLoginState(state: Boolean)
    suspend fun storeTokenKey(tokenKey: String)
}