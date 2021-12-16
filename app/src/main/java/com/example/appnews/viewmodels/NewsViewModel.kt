package com.example.appnews.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appnews.models.New
import com.example.appnews.repository.NewsRepository


class NewsViewModel : ViewModel() {
    val newsEveryThing: MutableLiveData<List<New>?> = MutableLiveData()
    val newsTopHeadlines: MutableLiveData<List<New>?> = MutableLiveData()

    fun getNewsEveryThing(query: String, from: String, sortBy: String, apiKey: String) {
        NewsRepository.getInstance()
            .getNewsEveryThing(query, from, sortBy, apiKey) { isSuccess, respone ->
                if (isSuccess) {
                    if (respone?.articles?.size == 0) {
                        newsEveryThing.postValue(null)
                    } else {
                        newsEveryThing.postValue(respone?.articles)
                    }
                } else {
                    newsEveryThing.postValue(null)
                }
            }
    }

    fun getNewsTopHeadlines(country: String, category: String, apiKey: String) {
        NewsRepository.getInstance()
            .getNewsTopHeadlines(country, category, apiKey) { isSuccess, respone ->
                if (isSuccess) {
                    if (respone?.articles?.size == 0) {
                        newsTopHeadlines.postValue(null)
                    } else {
                        newsTopHeadlines.postValue(respone?.articles)
                    }
                } else {
                    newsTopHeadlines.postValue(null)
                }
            }
    }
}