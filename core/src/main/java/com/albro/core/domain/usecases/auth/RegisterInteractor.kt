package com.albro.core.domain.usecases.auth

import com.albro.core.data.repositories.AuthRepository
import com.albro.core.domain.models.Register
import com.albro.core.domain.usecases.auth.interfaces.RegisterUseCase
import com.albro.core.utils.UiState
import kotlinx.coroutines.flow.Flow

class RegisterInteractor(private val authRepository: AuthRepository) : RegisterUseCase {
    override suspend fun invoke(requestBody: HashMap<String, String>): Flow<UiState<Register>> =
        authRepository.register(requestBody)
}