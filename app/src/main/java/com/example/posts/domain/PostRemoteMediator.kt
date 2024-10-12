package com.example.posts.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.posts.data.api.ApiService
import com.example.posts.data.db.PostDao
import com.example.posts.data.model.PostEntity

@OptIn(ExperimentalPagingApi::class)
class PostRemoteMediator(
    private val apiService: ApiService,
    private val postDao: PostDao
) : RemoteMediator<Int, PostEntity>() {
    private var offset = 0
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PostEntity>
    ): MediatorResult {
        return try {
            val response = apiService.getPosts(offset, state.config.pageSize + offset)
            offset += state.config.pageSize
            postDao.insertAll(response.map { PostEntity(it.id, it.title, it.images[0]) })
            MediatorResult.Success(endOfPaginationReached = response.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}