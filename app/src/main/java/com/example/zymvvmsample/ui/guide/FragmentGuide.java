package com.example.zymvvmsample.ui.guide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.common.CommonBindingAdapters;
import com.example.network.rxbus.RxBus;
import com.example.zymvvmsample.R;
import com.example.zymvvmsample.databinding.FragmentGuideBinding;

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

public class FragmentGuide extends Fragment implements View.OnClickListener {
    FragmentGuideBinding mBinding;
    public static final String GUIDE_URL = "guide_url";
    public static final String IS_LAST_FRAGMENT = "is_last_fragment";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_guide, container, false);
        if(getArguments().containsKey(GUIDE_URL)) {
            CommonBindingAdapters.loadImage(mBinding.guideImageview, Integer.valueOf(getArguments().getString(GUIDE_URL)));
        }
        mBinding.btnGuideEnter.setVisibility(getArguments().getBoolean(IS_LAST_FRAGMENT) ? View.VISIBLE : View.GONE);
        mBinding.btnGuideEnter.setOnClickListener(this);
        return mBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_guide_enter) {
            RxBus.getDefault().post(new StartMainAppEvent());
        }
    }
}
