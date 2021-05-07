package com.example.base.core.autoservice;

import java.util.ServiceLoader;

public final class XiangxueServiceLoader {
    private XiangxueServiceLoader() {
    }

    public static <S> S load(Class<S> service) {
        try {
            return ServiceLoader.load(service).iterator().next();
        } catch (Exception e) {
            return null;
        }
    }
}