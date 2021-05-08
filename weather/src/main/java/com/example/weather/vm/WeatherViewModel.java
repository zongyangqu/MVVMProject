package com.example.weather.vm;

import com.example.base.core.customview.BaseCustomViewModel;
import com.example.base.core.viewmodel.MvvmBaseViewModel;
import com.example.weather.model.WeatherModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2021/05/08
 * desc   :
 * version: 1.0
 */


public class WeatherViewModel extends MvvmBaseViewModel<WeatherModel, BaseCustomViewModel> {

    public static class WeatherViewModelFactory implements ViewModelProvider.Factory {
        private String city;
        public WeatherViewModelFactory(String city) {
            this.city = city;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new WeatherViewModel(city);
        }
    }

    public WeatherViewModel(String city) {
        model = new WeatherModel(city);
    }
}
