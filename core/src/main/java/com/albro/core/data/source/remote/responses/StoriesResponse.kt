package com.albro.core.data.source.remote.responses

import com.squareup.moshi.Json

data class StoriesResponse (
    @Json(name="listStory")
    val listStory: ArrayList<ListStoryItem?>? = null,

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
)