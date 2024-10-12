package com.example.posts.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.posts.data.model.PostEntity

@Dao
interface PostDao {
    @Query("SELECT * FROM postentity")
    fun getPosts(): PagingSource<Int, PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<PostEntity>)

    @Query("SELECT * FROM postentity WHERE id = :postId")
    suspend fun getPostById(postId: Int): PostEntity
}