package com.albro.storyapp.core.data.source.remote.responses

import com.squareup.moshi.Json

data class StoriesResponse (
    @Json(name="listStory")
    val listStory: List<ListStoryItem?>? = null,

    @Json(name="error")
    val error: Boolean? = null,

    @Json(name="message")
    val message: String? = null
)

data class ListStoryItem(
    @Json(name="photoUrl")
    val photoUrl: String? = null,

    @Json(name="createdAt")
    val createdAt: String? = null,

    @Json(name="name")
    val name: String? = null,

    @Json(name="description")
    val description: String? = null,

    @Json(name="id")
    val id: String? = null,

    @Json(name="lat")
    val lat: Double? = null,

    @Json(name="lon")
    val lon: Double? = null,
)