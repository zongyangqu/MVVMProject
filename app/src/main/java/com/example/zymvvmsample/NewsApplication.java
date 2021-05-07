package com.example.zymvvmsample;

import android.app.Application;

import com.example.base.core.loadsir.CustomCallback;
import com.example.base.core.loadsir.EmptyCallback;
import com.example.base.core.loadsir.ErrorCallback;
import com.example.base.core.loadsir.LoadingCallback;
import com.example.base.core.loadsir.TimeoutCallback;
import com.example.base.core.preference.PreferencesUtil;
import com.example.base.core.utils.ToastUtil;
import com.example.network.base.NetworkApi;
import com.kingja.loadsir.core.LoadSir;

public class NewsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesUtil.init(this);
        NetworkApi.init(new NetworkRequestInfo(this));
        ToastUtil.init(this);
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();
    }
}
