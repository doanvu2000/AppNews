package com.example.appnews.repository

import com.example.appnews.api.NewsService
import com.example.appnews.models.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository {
    companion object {
        private var instance: NewsRepository? = null
        fun getInstance() = instance ?: NewsRepository().also { instance = it }
    }

    fun getNewsEveryThing(
        query: String,
        from: String,
        sortBy: String,
        apiKey: String,
        result: (isSuccess: Boolean, respone: News?) -> Unit
    ) {
        NewsService.create().getEveryThing(query, from, sortBy, apiKey)
            .enqueue(object : Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    if (response.isSuccessful && response != null) {
                        result(true, response.body())
                    } else {
                        result(false, null)
                    }
                }

                override fun onFailure(call: Call<News>, t: Throwable) {

                }
            })
    }
    fun getNewsTopHeadlines(
        country: String,
        category: String,
        apiKey: String,
        result: (isSuccess: Boolean, respone: News?) -> Unit
    ){
        NewsService.create().getTopHeadlines(country, category,apiKey).enqueue(object :Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful && response != null) {
                    result(true, response.body())
                } else {
                    result(false, null)
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {

            }
        })
    }
}