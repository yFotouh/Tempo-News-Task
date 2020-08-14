package com.task.temponewstask.viewmodel

import androidx.lifecycle.LiveData
import com.task.temponewstask.model.dto.response.NewsResponse
import com.task.temponewstask.model.repository.NewsRepository
import com.task.temponewstask.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(newsRepository: NewsRepository) : BaseViewModel() {
    private val mutableLiveData =
        SingleLiveEvent<NewsResponse>()
    private var newsRepositoryImpl: NewsRepository? = null


    init {
        newsRepositoryImpl = newsRepository
    }

    fun getNews(query: String): LiveData<NewsResponse> {

        coroutineScope.launch(Dispatchers.Main) {
            progressVisibility.value = true
            var newsResponse = fetchNewsByQuery(query)
            mutableLiveData.value = newsResponse
            progressVisibility.postValue(false)
        }
        return mutableLiveData

    }

    suspend fun fetchNewsByQuery(query: String): NewsResponse {
        return withContext(Dispatchers.IO)
        {
            newsRepositoryImpl?.getNewsData(query)
        }!!
    }




}

