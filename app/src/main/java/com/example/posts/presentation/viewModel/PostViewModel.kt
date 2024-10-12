package com.example.posts.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.posts.data.model.PostEntity
import com.example.posts.domain.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {

    val posts: Flow<PagingData<PostEntity>> = repository.getPosts()

    suspend fun getPostById(postId: Int): PostEntity {
        return repository.getPostById(postId)
    }
}