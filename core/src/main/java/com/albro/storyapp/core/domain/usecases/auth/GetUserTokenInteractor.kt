package com.albro.storyapp.core.domain.usecases.auth

import com.albro.storyapp.core.data.repositories.AuthRepository
import com.albro.storyapp.core.domain.usecases.auth.interfaces.GetUserTokenUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserTokenInteractor @Inject constructor(private val authRepository: AuthRepository):
    GetUserTokenUseCase {
    override fun invoke(): Flow<String> = authRepository.getTokenKey()
}