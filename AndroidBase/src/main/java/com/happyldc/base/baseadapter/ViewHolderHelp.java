package com.happyldc.base.baseadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author ldc
 * @Created at 2018/1/5 16:32.
 */

public class ViewHolderHelp {
    private View mRootView;
    private SparseArray<View> mViews;
    private Context mContext;
    private int mPosition;

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }
    public ViewHolderHelp(){}

    public int getPosition() {
        return mPosition;
    }

    public View getRootView() {
        return mRootView;
    }

    public ViewHolderHelp(Context context, View view, int position) {
        this.mRootView = view;
        this.mContext = context;
        this.mPosition = position;
        mViews = new SparseArray<>();
    }
    public ViewHolderHelp(Context context, View view) {
        this.mRootView = view;
        this.mContext = context;
        mViews = new SparseArray<>();
    }

    public <T extends View> T $(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mRootView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public ViewHolderHelp setPosition(int position) {
        this.mPosition = position;
        return this;
    }

    public Context getContext() {
        return mContext;
    }


    public ViewHolderHelp setText(int viewId, String text) {
        TextView textView = $(viewId);
        textView.setText(text);
        return this;
    }

    public ViewHolderHelp setBackgroundResource(int viewId, int color) {
        View view = $(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public ViewHolderHelp setVisibility(int viewId, int visibility) {
        View textView = $(viewId);
        textView.setVisibility(visibility);
        return this;
    }

    public ViewHolderHelp setTag(int viewId, Object object) {
        View textView = $(viewId);
        textView.setTag(object);
        return this;
    }

    public ViewHolderHelp setText(int viewId, int resId) {
        TextView textView = $(viewId);
        textView.setText(resId);
        return this;
    }

    public ViewHolderHelp setTextColor(int viewId, int color) {
        TextView textView = $(viewId);
        textView.setTextColor(color);
        return this;
    }

    public ViewHolderHelp setImageResource(int viewId, int resId) {
        ImageView view = $(viewId);
        view.setImageResource(resId);
        return this;
    }

    public ViewHolderHelp setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = $(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public ViewHolderHelp setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = $(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}
