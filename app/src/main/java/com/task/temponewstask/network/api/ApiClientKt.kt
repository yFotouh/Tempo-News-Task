package com.task.temponewstask.network.api

import com.task.temponewstask.model.dto.response.NewsResponse
import com.task.temponewstask.utils.TimeHelper
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClientKt {
    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("from") units: String = TimeHelper.getTodayTimeString(),
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("pageSize") pageSize: Int = 20
    ): NewsResponse

}