package com.example.network.base;

import com.example.network.commoninterceptor.CommonRequestInterceptor;
import com.example.network.commoninterceptor.CommonResponseInterceptor;
import com.example.network.environment.EnvironmentActivity;
import com.example.network.environment.IEnvironment;
import com.example.network.errorhandler.HttpErrorHandler;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2021/04/30
 * desc   :
 * version: 1.0
 */


public abstract  class NetworkApi implements IEnvironment {
    private static INetworkRequiredInfo iNetworkRequiredInfo;
    private static HashMap<String, Retrofit> retrofitHashMap = new HashMap<>();
    private String mBaseUrl;
    private OkHttpClient mOkHttpClient;
    private static boolean mIsFormal = true;

    public NetworkApi() {
        //抽象方法获取请求域名 交由子类实现   并支持切换网络环境
        if (!mIsFormal) {
            mBaseUrl = getTest();
        }
        mBaseUrl = getFormal();
    }

    public static void init(INetworkRequiredInfo networkRequiredInfo) {
        iNetworkRequiredInfo = networkRequiredInfo;
        //获取当前网络环境
        mIsFormal = EnvironmentActivity.isOfficialEnvironment(networkRequiredInfo.getApplicationContext());
    }

    protected Retrofit getRetrofit(Class service) {
        if (retrofitHashMap.get(mBaseUrl + service.getName()) != null) {
            return retrofitHashMap.get(mBaseUrl + service.getName());
        }
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(mBaseUrl);
        retrofitBuilder.client(getOkHttpClient());
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit retrofit = retrofitBuilder.build();
        retrofitHashMap.put(mBaseUrl + service.getName(), retrofit);
        return retrofit;
    }

    private OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
            if (getInterceptor() != null) {
                okHttpClientBuilder.addInterceptor(getInterceptor());
            }
            okHttpClientBuilder.addInterceptor(new CommonRequestInterceptor(iNetworkRequiredInfo));
            okHttpClientBuilder.addInterceptor(new CommonResponseInterceptor());
            //Debug模式下加入拦截器  打印请求日志
            if (iNetworkRequiredInfo != null &&(iNetworkRequiredInfo.isDebug())) {
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);
            }
            mOkHttpClient = okHttpClientBuilder.build();
        }
        return mOkHttpClient;
    }


    public <T> ObservableTransformer<T, T> applySchedulers(final Observer<T> observer) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                Observable<T> observable = (Observable<T>)upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        //子类实现  请求正常  但是要处理不符合不同返回业务码  这块给用户展示
                        .map(getAppErrorHandler())
                        //子类实现  请求异常   这块给开发人员展示
                        .onErrorResumeNext(new HttpErrorHandler<T>());
                observable.subscribe(observer);
                return observable;
            }
        };
    }

    protected abstract Interceptor getInterceptor();

    protected abstract <T> Function<T, T> getAppErrorHandler();

}
