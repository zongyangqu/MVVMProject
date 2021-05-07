package com.example.zymvvmsample;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.WindowManager;


import com.example.base.core.activity.MvvmActivity;
import com.example.base.core.preference.PreferencesUtil;
import com.example.base.core.viewmodel.MvvmBaseViewModel;
import com.example.zymvvmsample.databinding.ActivityMainBinding;
import com.example.zymvvmsample.home.MainFragment;
import com.example.zymvvmsample.home.MainFragmentPagerAdapter;
import com.example.zymvvmsample.ui.ad.AdFragment;
import com.example.zymvvmsample.ui.ad.IMainActivity;
import com.example.zymvvmsample.ui.guide.GuideFragment;



public class MainActivity extends MvvmActivity<ActivityMainBinding, MvvmBaseViewModel> implements IMainActivity, ViewPager.OnPageChangeListener {
    private static final String IS_SHOW_GUIDE = "is_show_guide";
    private Fragment mAdFragment = new AdFragment(this);
    private Fragment mGuideFragment = new GuideFragment(this);
    private MainFragment mHomeFragment = new MainFragment();
    private MainFragmentPagerAdapter mFragmentPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        if (PreferencesUtil.getInstance().getBoolean(IS_SHOW_GUIDE, true)) {
            mFragmentPagerAdapter.addFragment(mGuideFragment);
            PreferencesUtil.getInstance().setBoolean(IS_SHOW_GUIDE, false);
        } else {
            mFragmentPagerAdapter.addFragment(mAdFragment);
        }
        mFragmentPagerAdapter.addFragment(mHomeFragment);
        viewDataBinding.viewpageId.setOffscreenPageLimit(1);
        viewDataBinding.viewpageId.setAdapter(mFragmentPagerAdapter);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (isMainFragmentVisible()) {// Check if HomeFragment is going to show
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            showSystemUi();
        }
    }


    private void showSystemUi() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar supportActionBar = getSupportActionBar();
        if (null != supportActionBar) {
            supportActionBar.show();
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void removeMeAndGoNextFragment() {
        mFragmentPagerAdapter.removeIndex0Fragment();
    }

    private boolean isMainFragmentVisible() {
        return mFragmentPagerAdapter.getListSize() == 1;
    }

    @Override
    public void onBackPressed() {
        if (isMainFragmentVisible()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onRetryBtnClick() {

    }

    @Override
    protected MvvmBaseViewModel getViewModel() {
        return null;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

}
