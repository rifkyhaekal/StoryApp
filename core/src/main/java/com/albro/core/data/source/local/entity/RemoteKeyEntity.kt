package com.albro.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeyEntity(
    @PrimaryKey
    val id: String,
    val nextKey: Int?,
    val prevKey: Int?
)
