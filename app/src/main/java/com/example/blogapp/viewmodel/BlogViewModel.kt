package com.example.blogapp.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blogapp.data.entity.PostsResponse
import com.example.blogapp.repository.BlogRepository
import com.example.blogapp.utils.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogViewModel @Inject constructor(
    private val blogRepository: BlogRepository,
) : ViewModel() {

    var blogUIState = mutableStateOf(BlogUIState())

    private val _posts: MutableStateFlow<ResourceState<List<PostsResponse>>> =
        MutableStateFlow(ResourceState.Loading())
    val posts: StateFlow<ResourceState<List<PostsResponse>>> = _posts

    init {
        getPosts()
    }

    private fun getPosts() {
        Log.d("HomeScreen", "getPosts")
        viewModelScope.launch(Dispatchers.IO) {
            blogRepository.getPosts()
                .collectLatest { blogResponse ->
                    _posts.value = blogResponse
                }
        }
    }

    fun onEvent(event: BlogUIEvent) {
        when (event) {
            is BlogUIEvent.OpenPostDetail -> {
                blogUIState.value = blogUIState.value.copy(
                    selectedPost = event.post
                )
                event.onNavigate()
            }

            is BlogUIEvent.AddNewPost -> {
                viewModelScope.launch(Dispatchers.Main) {
                    blogRepository.addPost(event.title, event.date, event.description)
                        .collectLatest { blogResponse ->
                            when (blogResponse) {
                                is ResourceState.Loading -> {
                                    Log.d("AddNewPost", "Loading")
                                }

                                is ResourceState.Success -> {
                                    Log.d("AddNewPost", "Success")
                                    getPosts()
                                    event.onNavigate()
                                }

                                is ResourceState.Error -> {
                                    Log.d("AddNewPost", "Error")
                                    Toast.makeText(
                                        event.context,
                                        "Erro ao conectar com o servidor!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                }
            }
        }
    }
}