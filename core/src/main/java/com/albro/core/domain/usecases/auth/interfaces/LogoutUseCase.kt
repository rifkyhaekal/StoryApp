package com.albro.core.domain.usecases.auth.interfaces

interface LogoutUseCase {
    suspend operator fun invoke()
}