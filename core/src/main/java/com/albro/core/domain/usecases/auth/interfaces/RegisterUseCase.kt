package com.albro.core.domain.usecases.auth.interfaces

import com.albro.core.domain.models.Register
import com.albro.core.utils.UiState
import kotlinx.coroutines.flow.Flow

interface RegisterUseCase {
    suspend fun invoke(requestBody: HashMap<String, String>): Flow<UiState<Register>>
}