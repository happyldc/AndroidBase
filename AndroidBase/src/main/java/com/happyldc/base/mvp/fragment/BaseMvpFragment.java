package com.happyldc.base.mvp.fragment;


import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.Toast;

import com.happyldc.base.base.fragment.BaseFragment;
import com.happyldc.base.mvpconstraint.presenter.BasePresenter;
import com.happyldc.base.mvpconstraint.view.BaseView;

/**
 * @author ldc
 * @Created at 2019/4/13 10:26.
 */

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {
    protected T mPrensenter;

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
    protected abstract T createPresenter();

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
