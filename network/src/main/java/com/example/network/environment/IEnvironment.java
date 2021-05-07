package com.example.network.environment;

public interface IEnvironment {
    //正式环境域名
    String getFormal();

    //测试环境域名
    String getTest();
}
