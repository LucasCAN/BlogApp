package com.example.blogapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.blogapp.screens.AddPostScreen
import com.example.blogapp.screens.DetailScreen
import com.example.blogapp.screens.HomeScreen
import com.example.blogapp.viewmodel.BlogViewModel

@Composable
fun AppNavigationGraph(blogViewModel: BlogViewModel = hiltViewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HOME_SCREEN) {
        composable(Routes.HOME_SCREEN) {
            HomeScreen(navController, blogViewModel)
        }
        composable(Routes.DETAIL_SCREEN) {
            DetailScreen(navController, blogViewModel)
        }
        composable(Routes.ADD_POST_SCREEN) {
            AddPostScreen(navController, blogViewModel)
        }
    }
}