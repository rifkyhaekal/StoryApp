package com.albro.core.domain.usecases.auth.interfaces

import kotlinx.coroutines.flow.Flow

interface GetLoginStateUseCase {
    operator fun invoke(): Flow<Boolean>
}