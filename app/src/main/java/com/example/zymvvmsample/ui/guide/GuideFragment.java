package com.example.zymvvmsample.ui.guide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.network.rxbus.RxBus;
import com.example.network.rxbus.Subscribe;
import com.example.zymvvmsample.R;
import com.example.zymvvmsample.databinding.FragmentGuideHomeBinding;
import com.example.zymvvmsample.ui.ad.IMainActivity;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2021/05/07
 * desc   :
 * version: 1.0
 */


public class GuideFragment extends Fragment {
    private FragmentGuideHomeBinding mBinding;
    ArrayList<Integer> urls = new ArrayList<>();
    private IMainActivity iMainActivity;

    public GuideFragment(IMainActivity iMainActivity){
        this.iMainActivity = iMainActivity;
        urls.add(R.drawable.guide_test1);
        urls.add(R.drawable.guide_test2);
        urls.add(R.drawable.guide_test3);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_guide_home, container, false);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mBinding.guideViewpager.setAdapter(new PagerAdapter(getChildFragmentManager(), urls));
        RxBus.getDefault().register(this);
        return mBinding.getRoot();
    }

    @Subscribe
    public void onStartMainAppEvent(StartMainAppEvent event) {
        if(iMainActivity != null) {
            iMainActivity.removeMeAndGoNextFragment();
        }
    }
}
