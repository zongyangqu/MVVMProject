package com.example.news.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NewsApiInterface {
    @GET("release/news")
    Observable<NewsListBean> getNewsList(@Query("channelId") String channelId,
                                         @Query("channelName") String channelName,
                                         @Query("page") String page);

    @GET("release/channel")
    Observable<NewsChannelsBean> getNewsChannels();


    @GET("joke/content/list.php")
    Observable<JokeBean> getjoke(@Query("time") String time, @Query("key") String key);

    @GET("toutiao/index")
    Observable<TopNewsBean> getNews(@Query("type") String time, @Query("channelName") String channelName,@Query("page") String page,@Query("key") String key);
}
