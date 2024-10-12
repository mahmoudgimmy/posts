package com.example.posts.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.posts.data.model.PostEntity
import com.example.posts.presentation.viewModel.PostViewModel

@Composable
fun PostDetailScreen(
    postId: Int,
    viewModel: PostViewModel = hiltViewModel(),
    fallbackImage: Painter
) {
    val scope = rememberCoroutineScope()

    val post = remember { mutableStateOf(PostEntity()) }

    LaunchedEffect(scope) {
        post.value = viewModel.getPostById(postId)
    }
    val painter = rememberAsyncImagePainter(post.value.imageUrl)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = post.value.title)
        Spacer(modifier = Modifier.padding(16.dp))
        when (painter.state) {
            is coil.compose.AsyncImagePainter.State.Error -> {
                Image(
                    painter = fallbackImage,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )
            }

            else -> {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )
            }
        }
        Spacer(modifier = Modifier.padding(16.dp))
    }

}