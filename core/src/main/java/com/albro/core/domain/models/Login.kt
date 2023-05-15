package com.albro.core.domain.models

data class Login(
    val userId: String,
    val name: String,
    val token: String
)
