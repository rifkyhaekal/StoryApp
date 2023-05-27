package com.albro.storyapp.core.domain.usecases.auth

import com.albro.storyapp.core.data.repositories.AuthRepository
import com.albro.storyapp.core.domain.models.Register
import com.albro.storyapp.core.domain.usecases.auth.interfaces.RegisterUseCase
import com.albro.storyapp.core.utils.UiState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterInteractor @Inject constructor(private val authRepository: AuthRepository) :
    RegisterUseCase {
    override suspend fun invoke(requestBody: HashMap<String, String>): Flow<UiState<Register>> =
        authRepository.register(requestBody)
}