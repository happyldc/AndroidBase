package com.happyldc.base.loading;

import android.view.View;

import com.billy.android.loading.Gloading;
import com.happyldc.base.loading.view.GlobalLoadingStatusView;

/**
 * demo to show how to create a {@link Gloading.Adapter}
 *
 * @author billy.qi
 * @see GlobalLoadingStatusView
 * @since 19/3/18 18:37
 */
public class GlobalAdapter implements Gloading.Adapter {

    @Override
    public View getView(Gloading.Holder holder, View convertView, int status) {
        GlobalLoadingStatusView loadingStatusView = null;
        //reuse the old view, if possible
        if (convertView != null && convertView instanceof GlobalLoadingStatusView) {
            loadingStatusView = (GlobalLoadingStatusView) convertView;
        }
        if (loadingStatusView == null) {
            loadingStatusView = new GlobalLoadingStatusView(holder.getContext(), holder.getRetryTask());
        }

        Object data = holder.getData();
        if (data == null) {
            loadingStatusView.setStatus(status);
        } else {
            loadingStatusView.setStatus(status, data.toString());
        }


        //show or not show msg view
        boolean hideMsgView = Constants.HIDE_LOADING_STATUS_MSG.equals(data);
        loadingStatusView.setMsgViewVisibility(!hideMsgView);

        return loadingStatusView;
    }

}
