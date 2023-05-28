package com.albro.storyapp.core.data.source.remote.responses

import com.squareup.moshi.Json

data class StoryResponse(
    @Json(name="error")
    val error: Boolean? = null,

    @Json(name="message")
    val message: String? = null,

    @Json(name="loginResult")
    val loginResult: StoryResult? = null,
)

data class StoryResult(
    @Json(name="id")
    val id: String? = null,

    @Json(name="name")
    val name: String? = null,

    @Json(name="description")
    val description: String? = null,

    @Json(name="photoUrl")
    val photoUrl: String? = null,
)