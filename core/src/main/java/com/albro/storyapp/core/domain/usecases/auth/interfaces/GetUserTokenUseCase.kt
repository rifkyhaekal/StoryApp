package com.albro.storyapp.core.domain.usecases.auth.interfaces

import kotlinx.coroutines.flow.Flow

interface GetUserTokenUseCase {
    operator fun invoke(): Flow<String>
}