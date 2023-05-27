package com.albro.storyapp.core.domain.models

data class Login(
    val userId: String,
    val name: String,
    val token: String
)
