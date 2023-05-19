package com.albro.core.domain.usecases.auth

import com.albro.core.data.repositories.AuthRepository
import com.albro.core.domain.models.Login
import com.albro.core.domain.usecases.auth.interfaces.LoginUseCase
import com.albro.core.utils.UiState
import kotlinx.coroutines.flow.Flow

class LoginInteractor(private val authRepository: AuthRepository) : LoginUseCase {
    override suspend fun execute(requestBody: HashMap<String, String>): Flow<UiState<Login>> =
        authRepository.login(requestBody)

    override suspend fun setLoginState(state: Boolean) = authRepository.setLoginState(state)

    override suspend fun storeTokenKey(tokenKey: String) = authRepository.setTokenKey(tokenKey)
}