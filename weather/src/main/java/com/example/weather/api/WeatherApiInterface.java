package com.example.weather.api;

import com.example.weather.model.bean.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2021/05/08
 * desc   :
 * version: 1.0
 */


public interface WeatherApiInterface {

    @GET("simpleWeather/query")
    Observable<WeatherResponse> getWeatherNews(@Query("city") String city, @Query("key") String key);
}
