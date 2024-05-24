package com.example.blogapp.di

import com.example.blogapp.data.api.ApiService
import com.example.blogapp.data.datasource.BlogDataSource
import com.example.blogapp.repository.BlogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleDI {
    @Provides
    fun providesBaseUrl() = "http://192.168.3.3:5500/api/v1/"

    @Singleton
    @Provides
    fun provideRetrofit(
        BASE_URL: String
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideBlogDataSource(apiService: ApiService): BlogDataSource = BlogDataSource(apiService)

    @Provides
    @Singleton
    fun provideBlogRepository(blogDataSource: BlogDataSource): BlogRepository =
        BlogRepository(blogDataSource)
}