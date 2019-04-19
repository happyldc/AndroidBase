package com.happyldc.base.mvpconstraint.presenter;

import com.happyldc.base.mvpconstraint.view.BaseView;

/**
 * @author ldc
 * @Created at 2018/6/21 14:55.
 */

public abstract class BasePresenter<T extends BaseView> implements IBasePresenter<T> {
    protected T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
        start();
    }

    @Override
    public void detachView() {
        this.mView = null;
    }
}
