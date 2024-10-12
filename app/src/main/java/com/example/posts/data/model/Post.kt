package com.example.posts.data.model

data class Post(
    val id: Int,
    val title: String,
    val images: List<String> = emptyList()
)