package com.albro.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "stories")
@Parcelize
data class StoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    val name: String,
    val description: String,
    val photoUrl: String,
) : Parcelable