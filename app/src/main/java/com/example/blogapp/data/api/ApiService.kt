package com.example.blogapp.data.api

import com.example.blogapp.data.entity.PostRequest
import com.example.blogapp.data.entity.PostsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("posts/")
    suspend fun getPosts(): Response<List<PostsResponse>>

    @POST("posts/")
    suspend fun addPost(@Body postsRequest: PostRequest): Response<PostsResponse>
}