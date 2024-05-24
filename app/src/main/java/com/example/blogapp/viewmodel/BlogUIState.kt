package com.example.blogapp.viewmodel

import com.example.blogapp.data.entity.PostsResponse

data class BlogUIState(
    var selectedPost: PostsResponse? = null
)