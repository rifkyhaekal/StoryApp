package com.albro.core.domain.usecases.auth

import com.albro.core.data.repositories.AuthRepository
import com.albro.core.domain.usecases.auth.interfaces.GetUserTokenUseCase
import kotlinx.coroutines.flow.Flow

class GetUserTokenInteractor(private val authRepository: AuthRepository): GetUserTokenUseCase {
    override fun invoke(): Flow<String> = authRepository.getTokenKey()
}