package com.example.blogapp.data.entity

import com.google.gson.annotations.SerializedName

data class PostsResponse(
    @SerializedName("titulo")
    val title: String,
    @SerializedName("data")
    val date: String,
    @SerializedName("descricao")
    val description: String
)