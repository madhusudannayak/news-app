package com.example.newsapp.adapter

import com.example.newsapp.retrofits.response.Article

interface AdapterClickListeners {

    fun clickListeners(article: Article)
}