package com.example.posts.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.posts.data.api.ApiService
import com.example.posts.data.db.PostDao
import com.example.posts.data.model.PostEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val apiService: ApiService,
    private val postDao: PostDao
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getPosts(): Flow<PagingData<PostEntity>> {
        val pagingSourceFactory = { postDao.getPosts() }

        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            remoteMediator = PostRemoteMediator(apiService, postDao),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    suspend fun getPostById(postId: Int): PostEntity {
        return postDao.getPostById(postId)
    }
}