package com.happyldc.base.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.happyldc.base.R;
import com.happyldc.base.loading.LoadingHelper;

/**
 * @author ldc
 * @Created at 2019/4/13 10:12.
 */

public abstract class BaseFragment extends Fragment {

    protected LoadingHelper loadingHelper;
    private View mContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        beforeCreateView(savedInstanceState);
        mContentView = createView(inflater, container, savedInstanceState);
        if (mContentView == null) {
            throw new RuntimeException("Fragment contentView can not be null");
        }
        initView(mContentView, savedInstanceState);
        initLoadingHelper(mContentView);

        setupView(mContentView, savedInstanceState);
        mContentView = getContentView();
        return mContentView;
    }

    /**
     * 如果 initLoadingHelper（mContentView） 则返回的View为loadingHelper.getHolder().getWrapper();
     * 否则返回mcontentView
     *
     * @return
     */
    protected View getContentView() {
        return loadingHelper.getHolder().getWrapper();
    }

    protected abstract void initView(View contentView, Bundle savedInstanceState);

    protected void initLoadingHelper(View targetView) {
        loadingHelper = new LoadingHelper(targetView, new Runnable() {
            @Override
            public void run() {
                onRetryLoadClick();
            }
        });
    }

    protected void onRetryLoadClick() {
    }

    protected void beforeCreateView(Bundle savedInstanceState) {
    }


    protected abstract void setupView(View contentView, Bundle savedInstanceState);

    /**
     * 创建视图
     * <p>当{@link #getLayoutResId()}<=0时需要重写本方法创建一个视图</p>
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(), container, false);
    }

    /**
     * 设置当前fragment布局Id
     *
     * @return 返回值如果小于等于0 时需要重写{@link #createView}方法创建一个视图
     */
    protected abstract int getLayoutResId();
}
