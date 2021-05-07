package com.example.base.core.viewmodel;

import com.example.base.core.model.IBaseModelListener;
import com.example.base.core.model.MvvmBaseModel;
import com.example.base.core.model.PagingResult;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2021/05/07
 * desc   :
 * version: 1.0
 */
public abstract class MvvmBaseViewModel<M extends MvvmBaseModel, D> extends ViewModel implements LifecycleObserver, IBaseModelListener<List<D>> {
    protected M model;
    public MutableLiveData<List<D>> dataList = new MutableLiveData();
    public MutableLiveData<ViewStatus> viewStatusLiveData = new MutableLiveData();
    public MutableLiveData<String> errorMessage = new MutableLiveData();

    public MvvmBaseViewModel() {
        dataList.setValue(new ArrayList<D>());
        viewStatusLiveData.setValue(ViewStatus.LOADING);
        errorMessage.setValue("");
    }

    public void tryToRefresh() {
        if (model != null) {
            model.refresh();
        }
    }

    public void tryToLoadNextPage() {
        model.load();
    }

    @Override
    protected void onCleared() {
        if (model != null) {
            model.cancel();
        }
    }

    @Override
    public void onLoadFinish(MvvmBaseModel model, List<D> data, PagingResult... pagingResult) {
        if (model == this.model) {
            if (model.isPaging()) {
                if (pagingResult[0].isFirstPage) {
                    dataList.getValue().clear();
                }
                if (pagingResult[0].isEmpty) {
                    if (pagingResult[0].isFirstPage) {
                        viewStatusLiveData.setValue(ViewStatus.EMPTY);
                    } else {
                        viewStatusLiveData.setValue(ViewStatus.NO_MORE_DATA);
                    }
                } else {
                    dataList.getValue().addAll(data);
                    dataList.postValue(dataList.getValue());
                    viewStatusLiveData.setValue(ViewStatus.SHOW_CONTENT);
                }
            } else {
                dataList.getValue().clear();
                dataList.getValue().addAll(data);
                dataList.postValue(dataList.getValue());
                viewStatusLiveData.setValue(ViewStatus.SHOW_CONTENT);
            }
            viewStatusLiveData.postValue(viewStatusLiveData.getValue());
        }
    }

    @Override
    public void onLoadFail(MvvmBaseModel model, String prompt, PagingResult... pagingResult) {
        errorMessage.setValue(prompt);
        if(model.isPaging() && !pagingResult[0].isFirstPage) {
            viewStatusLiveData.setValue(ViewStatus.LOAD_MORE_FAILED);
        } else {
            viewStatusLiveData.setValue(ViewStatus.REFRESH_ERROR);
        }
        viewStatusLiveData.postValue(viewStatusLiveData.getValue());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onResume() {
        if(dataList == null || dataList.getValue() == null || dataList.getValue().size() == 0) {
            model.getCachedDataAndLoad();
        } else {
            dataList.postValue(dataList.getValue());
            viewStatusLiveData.postValue(viewStatusLiveData.getValue());
        }
    }
}
