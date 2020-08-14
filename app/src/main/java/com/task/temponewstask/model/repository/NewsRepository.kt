package com.task.temponewstask.model.repository

import com.task.temponewstask.model.dto.response.NewsResponse

interface NewsRepository {
    suspend fun getNewsData(city: String): NewsResponse
}