package com.happyldc.base.mvp.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.happyldc.base.base.fragment.BaseListFragment;
import com.happyldc.base.mvpconstraint.presenter.BasePresenter;
import com.happyldc.base.mvpconstraint.view.BaseView;

/**
 * @author ldc
 * @Created at 2019/4/13 16:02.
 */

public abstract class BaseMvpListFragment <P extends BasePresenter,E>  extends BaseListFragment<E> implements BaseView {
    protected P mPrensenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    protected abstract P createPresenter();

    @Override
    public boolean isActive() {
        return isAdded();
    }


    @Override
    public void showToast(CharSequence message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
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
    public void onDestroy() {
        super.onDestroy();
        mPrensenter.detachView();
    }
}
