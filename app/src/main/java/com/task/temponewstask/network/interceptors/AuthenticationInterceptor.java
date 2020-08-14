package com.task.temponewstask.network.interceptors;


import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {


    public AuthenticationInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder().addQueryParameter("apiKey","55f98c3a3b6b477bb253f446c4a42185").build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}