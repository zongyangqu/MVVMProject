package com.example.news.headlinenews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.news.R;
import com.example.news.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

public class HeadlineNewsFragment extends Fragment {
    public HeadlineNewsFragmentAdapter adapter;
    FragmentHomeBinding viewDataBinding;
    HeadlineNewsViewModel viewModel = new HeadlineNewsViewModel();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        adapter = new HeadlineNewsFragmentAdapter(getChildFragmentManager());
        viewDataBinding.tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewDataBinding.viewpager.setAdapter(adapter);
        viewDataBinding.tablayout.setupWithViewPager(viewDataBinding.viewpager);
        viewDataBinding.viewpager.setOffscreenPageLimit(1);
        viewModel.dataList.observe(this, new Observer<List<ChannelsModel.Channel>>() {
            @Override
            public void onChanged(List<ChannelsModel.Channel> channels) {
                adapter.setChannels(channels);
                viewDataBinding.viewpager.getAdapter().notifyDataSetChanged();
            }
        });
        return viewDataBinding.getRoot();
    }

}
