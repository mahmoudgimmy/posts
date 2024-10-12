package com.example.posts.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.posts.R
import com.example.posts.presentation.view.PostDetailScreen
import com.example.posts.presentation.view.PostsListScreen

@Composable
fun PostNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "post_list") {

        composable("post_list") {
            PostsListScreen(navController)
        }

        composable("post_detail/{postId}") { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")?.toInt() ?: 0
            PostDetailScreen(
                postId,
                fallbackImage = painterResource(id = R.drawable.image)
            )
        }
    }

}
