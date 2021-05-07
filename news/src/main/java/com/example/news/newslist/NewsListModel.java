package com.example.news.newslist;

import android.text.TextUtils;

import com.example.base.core.customview.BaseCustomViewModel;
import com.example.base.core.model.MvvmBaseModel;
import com.example.common.views.picturetitleview.PictureTitleViewViewModel;
import com.example.common.views.titleview.TitleViewViewModel;
import com.example.network.api.TecentNetworkApi;
import com.example.network.api.VJHNetworkApi;
import com.example.network.observer.BaseObserver;
import com.example.news.api.NewsApiInterface;
import com.example.news.api.NewsListBean;
import com.example.news.api.TopNewsBean;

import java.util.ArrayList;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2021/05/07
 * desc   :
 * version: 1.0
 */


public class NewsListModel extends MvvmBaseModel<TopNewsBean, ArrayList<BaseCustomViewModel>> {
    private String mChannelId = "";
    private String mChannelName = "";

    public NewsListModel(String channelId, String channelName) {
        super(true, "pref_key_news_" + channelId, null, 1);
        mChannelId = channelId;
        mChannelName = channelName;
    }

    @Override
    public void load() {
        VJHNetworkApi.getService(NewsApiInterface.class)
                .getNews(mChannelId, mChannelName, String.valueOf(pageNumber),"761fc4e2bffe6ed2997b3626a642c3e0")
                .compose(VJHNetworkApi.
                        getInstance().
                        applySchedulers(new BaseObserver<TopNewsBean>(this, this)));
    }

    @Override
    public void onSuccess(TopNewsBean newsListBean, boolean isFromCache) {
        ArrayList<BaseCustomViewModel> baseViewModels = new ArrayList<>();
        for (TopNewsBean.NewsBean.TopNews source : newsListBean.result.data) {
            if (!TextUtils.isEmpty(source.thumbnail_pic_s03) ) {
                PictureTitleViewViewModel viewModel = new PictureTitleViewViewModel();
                viewModel.avatarUrl = source.thumbnail_pic_s03;
                viewModel.jumpUri = source.url;
                viewModel.title = source.title;
                baseViewModels.add(viewModel);
            } else {
                TitleViewViewModel viewModel = new TitleViewViewModel();
                viewModel.jumpUri = source.url;
                viewModel.title = source.title;
                baseViewModels.add(viewModel);
            }
        }
        notifyResultToListeners(newsListBean, baseViewModels, isFromCache);
    }

    @Override
    public void onFailure(Throwable e) {
        e.printStackTrace();
        loadFail(e.getMessage());
    }
}

