package com.happyldc.base.loading;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;


import com.billy.android.loading.Gloading;

/**
 * @author ldc
 * @Created at 2019/4/13 9:42.
 */

public class LoadingHelper {
    protected Gloading.Holder mHolder;
    private Object target;
    public Runnable retryRunable;

    public LoadingHelper(Activity target, Runnable retryRunable) {
        this.target = target;
        this.retryRunable = retryRunable;
    }

    public LoadingHelper(View target, Runnable retryRunable) {
        this.target = target;
        this.retryRunable = retryRunable;
    }

    public Gloading.Holder getHolder() {
        return mHolder;
    }

    private void initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            if (target instanceof Activity) {
                mHolder = Gloading.getDefault().wrap((Activity) this.target).withRetry(this.retryRunable);
            }
        }
        if (mHolder == null) {
            if (target instanceof View) {
                mHolder = Gloading.getDefault().wrap((View) this.target).withRetry(this.retryRunable);
            }
        }
    }

    public void showLoading() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoading();
    }

    public void showLoadSuccess() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoadSuccess();
    }

    public void showLoadFailed() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoadFailed();

    }

    public void showEmpty() {
        initLoadingStatusViewIfNeed();
        mHolder.showEmpty();
    }

}
