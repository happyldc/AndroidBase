package com.happyldc.base.mvp.activity;

import android.content.Context;
import android.widget.Toast;

import com.happyldc.base.base.activity.BaseActivity;
import com.happyldc.base.mvpconstraint.presenter.BasePresenter;
import com.happyldc.base.mvpconstraint.view.BaseView;


/**
 * @author ldc
 * @Created at 2019/4/13 10:21.
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {
    protected T mPrensenter;

    @Override
    protected void onResume() {
        super.onResume();
        if (mPrensenter == null) {
            mPrensenter = createPresenter();
            mPrensenter.attachView(this);
        }
    }

    /**
     * 创建 Presenter对象
     *
     * @return
     */
    protected abstract T createPresenter();

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public boolean isActive() {
        return !isFinishing();
    }


    @Override
    public void showToast(CharSequence message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadWaiting() {
        loadingHelper.showLoading();
    }

    @Override
    public void showLoadEmptyView() {
        loadingHelper.showEmpty();
    }

    @Override
    public void showLoadFailure(CharSequence failureMsg) {
        loadingHelper.showLoadFailed(failureMsg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPrensenter.detachView();
    }
}
