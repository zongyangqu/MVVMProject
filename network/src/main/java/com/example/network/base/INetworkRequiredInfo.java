package com.example.network.base;

import android.app.Application;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2021/04/30
 * desc   :
 * version: 1.0
 */


public interface INetworkRequiredInfo {

    String getAppVersionName();

    String getAppVersionCode();

    boolean isDebug();

    Application getApplicationContext();
}
