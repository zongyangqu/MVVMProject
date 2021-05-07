package com.example.news;

import com.example.common.autoservice.INewsService;
import com.example.news.headlinenews.HeadlineNewsFragment;
import com.google.auto.service.AutoService;

import androidx.fragment.app.Fragment;

@AutoService({INewsService.class})
public class NewsService implements INewsService {
    @Override
    public Fragment getHeadlineNewsFragment() {
        return new HeadlineNewsFragment();
    }
}
