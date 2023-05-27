package com.albro.storyapp.core.domain.usecases.auth

import com.albro.storyapp.core.data.repositories.AuthRepository
import com.albro.storyapp.core.domain.usecases.auth.interfaces.LoginUseCase
import com.albro.storyapp.core.domain.usecases.auth.interfaces.LogoutUseCase
import javax.inject.Inject

class LogoutInteractor @Inject constructor(
    private val authRepository: AuthRepository,
    private val loginUseCase: LoginUseCase
) : LogoutUseCase {

    override suspend fun invoke() {
        authRepository.deleteTokenKey()
        loginUseCase.setLoginState(false)
    }

}