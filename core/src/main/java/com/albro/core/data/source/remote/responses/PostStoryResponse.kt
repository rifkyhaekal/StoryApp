package com.albro.core.data.source.remote.responses

import com.squareup.moshi.Json

data class PostStoryResponse (
    @Json(name="error")
    val error: Boolean? = null,

    @Json(name="message")
    val message: String? = null
)