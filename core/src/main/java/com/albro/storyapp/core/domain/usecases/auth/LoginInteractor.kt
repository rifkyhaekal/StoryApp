package com.albro.storyapp.core.domain.usecases.auth

import com.albro.storyapp.core.data.repositories.AuthRepository
import com.albro.storyapp.core.domain.models.Login
import com.albro.storyapp.core.domain.usecases.auth.interfaces.LoginUseCase
import com.albro.storyapp.core.utils.UiState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginInteractor @Inject constructor(private val authRepository: AuthRepository) :
    LoginUseCase {
    override suspend fun execute(requestBody: HashMap<String, String>): Flow<UiState<Login>> =
        authRepository.login(requestBody)

    override suspend fun setLoginState(state: Boolean) = authRepository.setLoginState(state)

    override suspend fun storeTokenKey(tokenKey: String) = authRepository.setTokenKey(tokenKey)
}