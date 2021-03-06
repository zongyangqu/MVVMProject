package com.example.news.headlinenews;

import com.example.base.core.viewmodel.MvvmBaseViewModel;

/**
 * Created by Allen on 2017/7/20.
 * 保留所有版权，未经允许请不要分享到互联网和其他人
 */
public class HeadlineNewsViewModel extends MvvmBaseViewModel<ChannelsModel, ChannelsModel.Channel> {
    public HeadlineNewsViewModel(){
        model = new ChannelsModel();
        model.register(this);
        model.getCachedDataAndLoad();
    }
}
