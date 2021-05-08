package com.example.weather.model;

import android.util.Log;

import com.example.base.core.customview.BaseCustomViewModel;
import com.example.base.core.model.MvvmBaseModel;
import com.example.common.Constants;
import com.example.network.api.VJHNetworkApi;
import com.example.network.api.WeatherNetworkApi;
import com.example.network.observer.BaseObserver;
import com.example.weather.api.WeatherApiInterface;
import com.example.weather.model.bean.WeatherResponse;

import java.util.ArrayList;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2021/05/08
 * desc   :
 * version: 1.0
 */


public class WeatherModel extends MvvmBaseModel<WeatherResponse, ArrayList<BaseCustomViewModel>> {
    private String cityName;

    public WeatherModel(String cityname) {
        super(false, "pref_key_weather", null, 1);
        this.cityName = cityname;
    }

    @Override
    public void load() {
        WeatherNetworkApi.getService(WeatherApiInterface.class)
                .getWeatherNews(cityName,Constants.WEATHER_KEY)
                .compose(WeatherNetworkApi.
                        getInstance().
                        applySchedulers(new BaseObserver<WeatherResponse>(this, this)));
    }

    @Override
    public void onSuccess(WeatherResponse t, boolean isFromCache) {
        Log.d(Constants.TAG,t.result.city);
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
