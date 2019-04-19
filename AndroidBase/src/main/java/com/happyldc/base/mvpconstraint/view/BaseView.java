package com.happyldc.base.mvpconstraint.view;

import com.happyldc.base.mvpconstraint.presenter.IBasePresenter;

/**
 * @author ldc
 * @Created at 2018/6/21 14:53.
 */

public interface BaseView<T extends IBasePresenter> extends IBaseView {



    /**
     * Toast
     *
     * @param message
     */
    void showToast(CharSequence message);


}
