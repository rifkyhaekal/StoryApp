package com.albro.storyapp.core.utils

import com.albro.storyapp.core.data.source.local.entity.StoryEntity
import com.albro.storyapp.core.data.source.remote.responses.LoginResponse
import com.albro.storyapp.core.data.source.remote.responses.PostStoryResponse
import com.albro.storyapp.core.data.source.remote.responses.RegisterResponse
import com.albro.storyapp.core.data.source.remote.responses.StoriesResponse
import com.albro.storyapp.core.domain.models.Login
import com.albro.storyapp.core.domain.models.Register
import com.albro.storyapp.core.domain.models.Story
import com.albro.storyapp.core.domain.models.UploadStory

fun LoginResponse?.mapToDomain(): Login {
    return Login(
        this?.loginResult?.userId ?: "",
        this?.loginResult?.name ?: "",
        this?.loginResult?.token ?: ""
    )
}

fun RegisterResponse?.mapToDomain(): Register {
    return Register(this?.error ?: false, this?.message ?: "")
}

fun StoriesResponse?.mapToDomain(): ArrayList<Story> {
    val stories = arrayListOf<Story>()
    this?.listStory?.map { storyItem ->
        stories.add(
            Story(
                storyItem?.id ?: "",
                storyItem?.name ?: "",
                storyItem?.description ?: "",
                storyItem?.photoUrl ?: "",
                storyItem?.lat,
                storyItem?.lon,
            )
        )
    }
    return stories
}

fun PostStoryResponse?.mapToDomain(): UploadStory {
    return UploadStory(this?.error ?: false, this?.message ?: "")
}

fun StoryEntity.mapToDomain(): Story {
    return Story(
        id = this.id,
        name = this.name,
        description = this.description,
        photoUrl = this.photoUrl,
        lat = this.lat,
        lon = this.lon
    )
}

fun StoriesResponse?.mapToEntity(): ArrayList<StoryEntity> {
    val stories = arrayListOf<StoryEntity>()
    this?.listStory?.map { storyItem ->
        stories.add(
            StoryEntity(
                storyItem?.id ?: "",
                storyItem?.name ?: "",
                storyItem?.description ?: "",
                storyItem?.photoUrl ?: "",
                storyItem?.lat,
                storyItem?.lon
            )
        )
    }

    return stories
}

fun Story.mapToEntity(): StoryEntity {
    return StoryEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        photoUrl = this.photoUrl,
        lat = this.lat,
        lon = this.lon
    )
}
