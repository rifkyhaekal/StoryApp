package com.albro.core.domain.usecases.auth

import com.albro.core.data.repositories.AuthRepository
import com.albro.core.domain.usecases.auth.interfaces.LoginUseCase
import com.albro.core.domain.usecases.auth.interfaces.LogoutUseCase

class LogoutInteractor(
    private val authRepository: AuthRepository,
    private val loginUseCase: LoginUseCase
) : LogoutUseCase {

    override suspend fun invoke() {
        authRepository.deleteTokenKey()
        loginUseCase.setLoginState(false)
    }

}