package com.example.blogapp.repository

import android.util.Log
import com.example.blogapp.data.datasource.BlogDataSource
import com.example.blogapp.data.entity.PostsResponse
import com.example.blogapp.utils.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BlogRepository @Inject constructor(
    private val blogDataSource: BlogDataSource
) {
    suspend fun getPosts(): Flow<ResourceState<List<PostsResponse>>> {
        return flow {
            emit(ResourceState.Loading())

            val response = blogDataSource.getPosts()

            if (response.isSuccessful && response.body() != null) {
                emit(ResourceState.Success(response.body()!!))
            } else {
                emit(ResourceState.Error("Erro ao recuperar posts!"))
            }
        }.catch { e ->
            emit(ResourceState.Error(e.localizedMessage ?: "Aconteceu um erro - Flow"))
        }
    }

    suspend fun addPost(title: String, date: String, description: String): Flow<ResourceState<PostsResponse>> {
        return flow {
            emit(ResourceState.Loading())

            val response = blogDataSource.addPost(title, date, description)
            Log.d("BlogRepository", "response $response")

            if (response.isSuccessful && response.body() != null) {
                emit(ResourceState.Success(response.body()!!))
            } else {
                emit(ResourceState.Error("Erro ao recuperar posts!"))
            }
        }
            .catch { e ->
                emit(ResourceState.Error(e.localizedMessage ?: "Aconteceu um erro - Flow"))
            }
    }
}