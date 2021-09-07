package com.example.newsapp

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.newsapp.paging.NewsPagingSource
import com.example.newsapp.retrofits.NewsInterface
import com.example.newsapp.retrofits.response.Article

class NewsRepository(val newsInterface: NewsInterface) {

    fun getAllNewsStream(): LiveData<PagingData<Article>> = Pager(
            config = PagingConfig(
                    20,
                    5,
                    enablePlaceholders = false
            ),
            pagingSourceFactory = {
                NewsPagingSource(newsInterface)
            }
    ).liveData

}