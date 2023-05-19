package com.albro.core.domain.usecases.auth

import com.albro.core.data.repositories.AuthRepository
import com.albro.core.domain.usecases.auth.interfaces.GetLoginStateUseCase
import kotlinx.coroutines.flow.Flow

class GetLoginStateInteractor(private val authRepository: AuthRepository): GetLoginStateUseCase {
    override fun invoke(): Flow<Boolean> = authRepository.getLoginState()
}