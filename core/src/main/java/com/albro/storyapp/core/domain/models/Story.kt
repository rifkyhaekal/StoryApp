package com.albro.storyapp.core.domain.models

data class Story(
    val id: String,
    val name: String,
    val description: String,
    val photoUrl: String,
    val lat: Double?,
    val lon: Double?
)
