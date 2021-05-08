package com.example.weather.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.base.core.customview.BaseCustomViewModel;
import com.example.base.core.fragment.MvvmFragment;
import com.example.weather.R;
import com.example.weather.databinding.FragmentWeatherBinding;
import com.example.weather.vm.WeatherViewModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2021/05/08
 * desc   :
 * version: 1.0
 */


public class WeatherFragment extends MvvmFragment<FragmentWeatherBinding, WeatherViewModel, BaseCustomViewModel>{

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_weather;
    }

    @Override
    public WeatherViewModel getViewModel() {
        return ViewModelProviders.of(getActivity(), new WeatherViewModel.WeatherViewModelFactory("北京"))
                .get("weatherViewModel", WeatherViewModel.class);
    }

    @Override
    public void onListItemInserted(ArrayList<BaseCustomViewModel> sender) {

    }

    @Override
    protected void onRetryBtnClick() {

    }

    @Override
    protected String getFragmentTag() {
        return null;
    }
}
