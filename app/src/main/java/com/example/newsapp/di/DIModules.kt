package com.example.newsapp.di

import com.example.newsapp.NewsRepository
import com.example.newsapp.retrofits.NewsInterface
import com.example.newsapp.retrofits.response.NewsResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DIModules {
    private const val BASE_URL = "https://newsapi.org/v2/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    fun provideNewsInterFace(retrofit: Retrofit): NewsInterface {
        return retrofit.create(NewsInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(newsInterface: NewsInterface): NewsRepository {
        return NewsRepository(newsInterface)
    }
}