package com.happyldc.base.loading.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.happyldc.base.R;
import com.happyldc.util.NetWorkUtils;

import static com.billy.android.loading.Gloading.STATUS_EMPTY_DATA;
import static com.billy.android.loading.Gloading.STATUS_LOADING;
import static com.billy.android.loading.Gloading.STATUS_LOAD_FAILED;
import static com.billy.android.loading.Gloading.STATUS_LOAD_SUCCESS;


@SuppressLint("ViewConstructor")
public class GlobalLoadingStatusView extends LinearLayout implements View.OnClickListener {

    private final TextView mTextView;
    private final Runnable mRetryTask;
    private final ImageView mImageView;

    public GlobalLoadingStatusView(Context context, Runnable retryTask) {
        super(context);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        LayoutInflater.from(context).inflate(R.layout.view_global_loading_status, this, true);
        mImageView = findViewById(R.id.image);
        mTextView = findViewById(R.id.text);
        this.mRetryTask = retryTask;
        setBackgroundColor(0xFFF0F0F0);
    }

    public void setMsgViewVisibility(boolean visible) {
        mTextView.setVisibility(visible ? VISIBLE : GONE);
    }


    public void setStatus(int status, CharSequence tipMsg) {
        boolean show = true;
        OnClickListener onClickListener = null;
        int image = R.drawable.loading;
        CharSequence str = getResources().getString(R.string.str_none);
        switch (status) {
            case STATUS_LOAD_SUCCESS:
                show = false;
                break;
            case STATUS_LOADING:
                if (TextUtils.isEmpty(tipMsg)) {
                    str = getResources().getString(R.string.loading);
                } else {
                    str = tipMsg;
                }
                break;
            case STATUS_LOAD_FAILED:
                str = getResources().getString(R.string.load_failed);
                image = R.drawable.icon_failed;
                if (TextUtils.isEmpty(tipMsg)) {
                    boolean networkConn = NetWorkUtils.isNetWorkConnected();
                    if (!networkConn) {
                        str = getResources().getString(R.string.load_failed_no_network);
                        image = R.drawable.icon_no_wifi;
                    }
                } else {
                    str = tipMsg;
                }
                onClickListener = this;
                break;
            case STATUS_EMPTY_DATA:
                if (TextUtils.isEmpty(tipMsg)) {
                    str = getResources().getString(R.string.empty);
                } else {
                    str = tipMsg;
                }
                image = R.drawable.icon_empty;
                break;
            default:
                break;
        }
        mImageView.setImageResource(image);
        setOnClickListener(onClickListener);
        mTextView.setText(str);
        setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void setMsg(String msg) {
        mTextView.setText(msg == null ? "" : msg);
    }

    public void setStatus(int status) {
        setStatus(status, null);
    }

    @Override
    public void onClick(View v) {
        if (mRetryTask != null) {
            mRetryTask.run();
        }
    }
}
