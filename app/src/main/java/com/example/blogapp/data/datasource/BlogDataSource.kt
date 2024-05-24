package com.example.blogapp.data.datasource

import com.example.blogapp.data.api.ApiService
import com.example.blogapp.data.entity.PostRequest
import com.example.blogapp.data.entity.PostsResponse
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Inject

class BlogDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getPosts(): Response<List<PostsResponse>> = apiService.getPosts()
    suspend fun addPost(title: String, date: String, description: String): Response<PostsResponse> = apiService.addPost(PostRequest(title, date, description))
}