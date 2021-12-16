package com.example.appnews.models

data class News(
    val status: String,
    val totalResults: Int,
    val articles: List<New>
)