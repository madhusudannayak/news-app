package com.example.newsapp.retrofits

import com.example.newsapp.retrofits.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsInterface {

    @GET("top-headlines")
    suspend fun getAllNews(
            @Query("country") country: String,
            @Query("category") category: String,
            @Query("apiKey") apiKey: String,
            @Query("page") page: Int,
            @Query("pageSize") pageSize: Int
    ): NewsResponse

}