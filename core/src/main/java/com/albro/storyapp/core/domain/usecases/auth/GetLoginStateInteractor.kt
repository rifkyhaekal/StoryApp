package com.albro.storyapp.core.domain.usecases.auth

import com.albro.storyapp.core.data.repositories.AuthRepository
import com.albro.storyapp.core.domain.usecases.auth.interfaces.GetLoginStateUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoginStateInteractor @Inject constructor(private val authRepository: AuthRepository):
    GetLoginStateUseCase {
    override fun invoke(): Flow<Boolean> = authRepository.getLoginState()
}