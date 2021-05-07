package com.example.news.headlinenews;

import android.os.Parcelable;

import com.example.news.newslist.NewsListFragment;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by Allen on 2017/7/20.
 * 保留所有版权，未经允许请不要分享到互联网和其他人
 */
public class HeadlineNewsFragmentAdapter extends FragmentPagerAdapter {
    private List<ChannelsModel.Channel> mChannels;
    private int itemCount = 0;

    public HeadlineNewsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setChannels(List<ChannelsModel.Channel> channels) {
        this.mChannels = channels;
        itemCount = channels.size();
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int pos) {
//        return  new NewsListFragment();
        return NewsListFragment.newInstance(mChannels.get(pos).channelId, mChannels.get(pos).channelName);
    }

    @Override
    public int getCount() {
        return itemCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mChannels.get(position).channelName;
    }

    @Override
    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
    }
}