package com.example.network.api;

import com.example.network.base.NetworkApi;
import com.example.network.beans.TecentBaseResponse;
import com.example.network.beans.VJHBaseResponse;
import com.example.network.errorhandler.ExceptionHandle;
import com.example.network.utils.TecentUtil;


import java.io.IOException;

import io.reactivex.functions.Function;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2021/04/30
 * desc   :
 * version: 1.0
 */


public class VJHNetworkApi extends NetworkApi {
    private static volatile VJHNetworkApi sInstance;

    public static VJHNetworkApi getInstance() {
        if (sInstance == null) {
            synchronized (VJHNetworkApi.class) {
                if (sInstance == null) {
                    sInstance = new VJHNetworkApi();
                }
            }
        }
        return sInstance;
    }

    public static  <T> T getService(Class<T> service) {
        return getInstance().getRetrofit(service).create(service);
    }

    @Override
    protected Interceptor getInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String timeStr = TecentUtil.getTimeStr();
                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader("Source", "source");
                builder.addHeader("Authorization", TecentUtil.getAuthorization(timeStr));
                builder.addHeader("Date", timeStr);
                return chain.proceed(builder.build());
            }
        };
    }

    protected <T> Function<T, T> getAppErrorHandler() {
        return new Function<T, T>() {
            @Override
            public T apply(T response) throws Exception {
                //response中code码不会0 出现错误
                if (response instanceof VJHBaseResponse && ((VJHBaseResponse) response).error_code != 0) {
                    ExceptionHandle.ServerException exception = new ExceptionHandle.ServerException();
                    exception.code = ((VJHBaseResponse) response).error_code;
                    exception.message = ((VJHBaseResponse) response).reason != null ? ((VJHBaseResponse) response).error_code+"" : "";
                    throw exception;
                }
                return response;
            }
        };
    }

    @Override
    public String getFormal() {
        return "http://v.juhe.cn/";
    }

    @Override
    public String getTest() {
        return "http://v.juhe.cn/";
    }
}

