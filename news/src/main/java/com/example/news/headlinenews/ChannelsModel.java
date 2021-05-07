package com.example.news.headlinenews;


import com.example.base.core.model.MvvmBaseModel;
import com.example.network.api.TecentNetworkApi;
import com.example.network.observer.BaseObserver;
import com.example.news.api.NewsApiInterface;
import com.example.news.api.NewsChannelsBean;

import java.util.ArrayList;

/**
 * Created by Allen on 2017/7/20.
 * 保留所有版权，未经允许请不要分享到互联网和其他人
 */
public class ChannelsModel extends MvvmBaseModel<NewsChannelsBean, ArrayList<ChannelsModel.Channel>> {
    private static final String PREF_KEY_HOME_CHANNEL = "pref_key_home_channel1";
    public static final String PREDEFINED_CHANNELS= "{\"showapi_res_body\":{\"channelList\":[{\"channelId\":\"top\",\"name\":\"头条推荐\"},{\"channelId\":\"guonei\",\"name\":\"国内焦点\"},{\"channelId\":\"guoji\",\"name\":\"国际焦点\"},{\"channelId\":\"yule\",\"name\":\"娱乐焦点\"},{\"channelId\":\"tiyu\",\"name\":\"体育焦点\"},{\"channelId\":\"junshi\",\"name\":\"互军事焦点\"},{\"channelId\":\"keji\",\"name\":\"科技焦点\"},{\"channelId\":\"caijing\",\"name\":\"财经焦点\"},{\"channelId\":\"shishang\",\"name\":\"时尚焦点\"},{\"channelId\":\"youxi\",\"name\":\"游戏焦点\"},{\"channelId\":\"qiche\",\"name\":\"汽车焦点\"},{\"channelId\":\"jiankang\",\"name\":\"健康焦点\"}],\"ret_code\":0,\"totalNum\":48},\"showapi_res_code\":0,\"showapi_res_error\":\"\"}";
    public class Channel {
        public String channelId;
        public String channelName;
    }

    public ChannelsModel() {
        super(false, PREF_KEY_HOME_CHANNEL, PREDEFINED_CHANNELS);
    }

    @Override
    public void onSuccess(NewsChannelsBean newsChannelsBean, boolean isFromCache) {
        ArrayList<Channel> channels = new ArrayList<>();
        for (NewsChannelsBean.ChannelList source : newsChannelsBean.showapiResBody.channelList) {
            Channel channel = new Channel();
            channel.channelId = source.channelId;
            channel.channelName = source.name;
            channels.add(channel);
        }
        notifyResultToListeners(newsChannelsBean, channels, isFromCache);
    }

    @Override
    public void onFailure(Throwable e) {
        e.printStackTrace();
        loadFail(e.getMessage());
    }

    @Override
    public void load() {
       /* TecentNetworkApi.getService(NewsApiInterface.class)
                .getNewsChannels()
                .compose(TecentNetworkApi.
                        getInstance().
                        applySchedulers(new BaseObserver<NewsChannelsBean>(this, this)));*/
    }
}
