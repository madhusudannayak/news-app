package com.example.newsapp.retrofits.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
) : Parcelable