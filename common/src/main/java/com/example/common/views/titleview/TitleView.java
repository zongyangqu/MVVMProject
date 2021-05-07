package com.example.common.views.titleview;

import android.content.Context;
import android.util.Log;
import android.view.View;


import com.example.base.core.customview.BaseCustomView;
import com.example.common.R;
import com.example.common.databinding.TitleViewBinding;


/**
 * Created by Allen on 2017/7/20.
 * 保留所有版权，未经允许请不要分享到互联网和其他人
 */
public class TitleView extends BaseCustomView<TitleViewBinding, TitleViewViewModel> {
    public TitleView(Context context) {
        super(context);
    }

    @Override
    public int setViewLayoutId() {
        return R.layout.title_view;
    }

    @Override
    public void setDataToView(TitleViewViewModel data) {
        getDataBinding().setViewModel(data);
    }

    @Override
    public void onRootClick(View view) {
        Log.d("WebviewActivity",getViewModel().jumpUri);
        //WebviewActivity.startCommonWeb(view.getContext(), "", getViewModel().jumpUri);
    }
}
