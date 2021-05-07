package com.example.news.newslist;

import com.example.base.core.customview.BaseCustomViewModel;
import com.example.base.core.viewmodel.MvvmBaseViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2021/05/07
 * desc   :
 * version: 1.0
 */


public class NewsListViewModel extends MvvmBaseViewModel<NewsListModel, BaseCustomViewModel> {
    public static class NewsListViewModelFactory implements ViewModelProvider.Factory {
        private String classId;
        private String lboClassId;
        public NewsListViewModelFactory(String classId, String lboClassId) {
            this.classId = classId;
            this.lboClassId = lboClassId;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new NewsListViewModel(classId, lboClassId);
        }
    }

    public NewsListViewModel(String classId, String lboClassId) {
        model = new NewsListModel(classId, lboClassId);
        model.register(this);
    }
}
