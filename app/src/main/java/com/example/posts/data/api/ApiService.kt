package com.example.posts.data.api

import com.example.posts.data.model.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getPosts(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): List<Post>
}