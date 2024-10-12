package com.example.posts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.posts.presentation.navigation.PostNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController: NavHostController = rememberNavController()
            val currentDestination by navController.currentBackStackEntryAsState()

            MaterialTheme {
                Scaffold(topBar = {
                    MyToolbar(
                        title = when (currentDestination?.destination?.route) {
                            "post_detail/{postId}" -> "Post Details"
                            "post_list" -> "Posts List"
                            else -> "Posts List"
                        },
                        onBackClick = { navController.popBackStack() },
                        canNavigateBack = currentDestination?.destination?.route == "post_detail/{postId}"
                    )
                }) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(color = MaterialTheme.colors.background),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        PostNavGraph(navController)
                    }

                }
            }
        }
    }

    @Composable
    fun MyToolbar(title: String, onBackClick: () -> Unit, canNavigateBack: Boolean) {
        TopAppBar(title = { Text(text = title) },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            navigationIcon = if (canNavigateBack) {
                {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            } else null,
            elevation = 4.dp)
    }

}

