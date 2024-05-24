package com.example.blogapp.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.blogapp.R
import com.example.blogapp.components.ButtonAddPost
import com.example.blogapp.components.Loader
import com.example.blogapp.components.PostsList
import com.example.blogapp.navigation.Routes
import com.example.blogapp.utils.ResourceState
import com.example.blogapp.viewmodel.BlogUIEvent
import com.example.blogapp.viewmodel.BlogViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    blogViewModel: BlogViewModel = hiltViewModel()
) {
    val postsResponse by blogViewModel.posts.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
                title = {
                    Text(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        text = stringResource(R.string.blog_app),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center
                    )
                })
        },
        content = {
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                when (postsResponse) {
                    is ResourceState.Loading -> {
                        Log.d("HomeScreen", "Loading")
                        Loader()
                    }

                    is ResourceState.Success -> {
                        val response = (postsResponse as ResourceState.Success).data
                        PostsList(response, onItemClick = { item ->
                            blogViewModel.onEvent(
                                BlogUIEvent.OpenPostDetail(
                                    item,
                                    onNavigate = {
                                        navController.navigate(Routes.DETAIL_SCREEN)
                                    }
                                ))
                        })
                    }

                    is ResourceState.Error -> {
                        val error = (postsResponse as ResourceState.Error)
                        Log.d("HomeScreen", "Error $error")
                        Toast.makeText(context,
                            stringResource(R.string.erro_servidor), Toast.LENGTH_LONG).show()
                    }
                }
            }
        },
        bottomBar = {
            ButtonAddPost(navController, stringResource(R.string.novo_post))
        }
    )
}
