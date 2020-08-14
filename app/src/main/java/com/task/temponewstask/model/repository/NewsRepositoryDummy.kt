package com.task.temponewstask.model.repository

import com.task.temponewstask.model.dto.response.NewsResponse

class NewsRepositoryDummy : NewsRepository {

    override suspend fun getNewsData(query: String, pageNumber: Int): NewsResponse {
        TODO("Not yet implemented")
    }


}