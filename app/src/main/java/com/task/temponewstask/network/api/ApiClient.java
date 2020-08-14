package com.task.temponewstask.network.api;

import com.task.temponewstask.model.dto.response.NewsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

@Deprecated
public interface ApiClient {
    @GET("volumes/")
    Observable<NewsResponse> getBooksWithTitle(
            @Query("q") String title
    );

}
