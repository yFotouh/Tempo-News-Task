package com.task.temponewstask.network.interceptors;


import com.task.temponewstask.network.exceptions.NoConnectivityException;
import com.task.temponewstask.utils.ConnectionUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ConnectivityInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!ConnectionUtil.isOnline()) {
            throw new NoConnectivityException();
        }

        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }

}
