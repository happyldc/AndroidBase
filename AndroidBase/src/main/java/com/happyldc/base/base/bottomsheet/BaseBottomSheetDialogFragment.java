package com.happyldc.base.base.bottomsheet;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author ldc
 * @Created at 2019/4/13 11:20.
 */

public abstract class BaseBottomSheetDialogFragment extends BottomSheetDialogFragment {
    protected View mContentView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        beforeCreateView(savedInstanceState);
        mContentView = createView(inflater, container, savedInstanceState);
        if (mContentView == null) {
            throw new RuntimeException("Fragment contentView can not be null");
        }
        setupView(savedInstanceState);
        return mContentView;

    }

    protected void beforeCreateView(Bundle savedInstanceState) {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupView(savedInstanceState);
        setOnclick();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


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


    /**
     * 初始化
     *
     * @param savedInstanceState
     */
    protected void setupView(Bundle savedInstanceState) {
    }


    /**
     * 设置View监听事件
     */
    protected void setOnclick() {

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    protected <T extends View> T $(int id) {
        return (T) mContentView.findViewById(id);
    }


}
