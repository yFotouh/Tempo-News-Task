package com.task.temponewstask.model.repository

import com.task.temponewstask.model.dto.response.NewsResponse
import com.task.temponewstask.network.Api

class NewsRepositoryImpl : BaseRepository(), NewsRepository {

    override suspend fun getNewsData(query: String,pageNumber : Int): NewsResponse {
        return Api.getApiClientKt().getNews(query,pageNumber)
    }

}