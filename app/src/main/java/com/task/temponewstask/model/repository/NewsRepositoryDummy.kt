package com.task.temponewstask.model.repository

import com.task.temponewstask.model.dto.response.NewsResponse

class NewsRepositoryDummy : NewsRepository {

    override suspend fun getNewsData(city: String): NewsResponse {
        TODO("Not yet implemented")
    }



}