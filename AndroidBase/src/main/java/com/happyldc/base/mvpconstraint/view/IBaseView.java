package com.happyldc.base.mvpconstraint.view;

import android.content.Context;

/**
 * @author ldc
 * @Created at 2018/3/30 9:48.
 */

public interface IBaseView {
    /**
     * 获取上下文对象
     */
    public Context getContext();

    /**
     * 检查View是否是激活状态
     *
     * @return true 已激活 可以对View进行操作 false View无法被操作
     */
    boolean isActive();


    /**
     * Toast
     *
     * @param message
     */
    void showToast(CharSequence message);

    /***
     * 显示加载中
     */
    void showLoadWaiting();
    /**
     * 显示空视图
     */
    void showLoadEmptyView();

    /***
     * 显示失败
     * @param failureMsg 失败信息
     */
    void showLoadFailure(CharSequence failureMsg);



}
