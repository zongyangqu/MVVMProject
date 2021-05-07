package com.example.base.core.model;

public interface MvvmDataObserver<F> {
    void onSuccess(F t, boolean isFromCache);
    void onFailure(Throwable e);
}
