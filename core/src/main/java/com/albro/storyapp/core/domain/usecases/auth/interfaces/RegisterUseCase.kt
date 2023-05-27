package com.albro.storyapp.core.domain.usecases.auth.interfaces

import com.albro.storyapp.core.domain.models.Register
import com.albro.storyapp.core.utils.UiState
import kotlinx.coroutines.flow.Flow

interface RegisterUseCase {
    suspend operator fun invoke(requestBody: HashMap<String, String>): Flow<UiState<Register>>
}