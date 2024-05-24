package com.example.blogapp.viewmodel

import android.content.Context
import com.example.blogapp.data.entity.PostsResponse

sealed class BlogUIEvent {
    data class OpenPostDetail(val post: PostsResponse, val onNavigate: () -> Unit) : BlogUIEvent()
    data class AddNewPost(
        val title: String,
        val date: String,
        val description: String,
        val onNavigate: () -> Unit,
        val context: Context
    ) :
        BlogUIEvent()
}