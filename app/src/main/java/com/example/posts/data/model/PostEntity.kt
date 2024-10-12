package com.example.posts.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostEntity(
    @PrimaryKey val id: Int = 0,
    val title: String = "",
    val imageUrl: String = ""

)