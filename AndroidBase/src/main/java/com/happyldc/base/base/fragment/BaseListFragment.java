package com.happyldc.base.base.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.BaseAdapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.happyldc.base.R;


import java.util.ArrayList;
import java.util.List;

public abstract class BaseListFragment<T> extends BaseFragment {

    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager layoutManager;

    protected BaseQuickAdapter<T, BaseViewHolder> mAdapter;

    public BaseQuickAdapter<T, BaseViewHolder> getAdapter() {
        return mAdapter;
    }
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }
    @Override
    protected int getLayoutResId() {

        return R.layout.base_fragment_list;
    }

    @Override
    protected void setupView(View contentView, Bundle savedInstanceState) {
        initView(contentView, savedInstanceState);
        this.layoutManager = createLayoutManager();
        this.mAdapter = createAdapter();

        this.mRecyclerView.setLayoutManager(this.layoutManager);
        this.mRecyclerView.setAdapter(this.mAdapter);

    }

    public abstract RecyclerView.LayoutManager createLayoutManager();

    public abstract BaseQuickAdapter<T, BaseViewHolder> createAdapter();

    private void initView(View contentView, Bundle savedInstanceState) {
        mRecyclerView = contentView.findViewById(R.id.base_recyclerview);
    }

    public void setData(List<T> data) {
        mAdapter.setNewData(data);
    }

    public void addData(List<T> data) {
        mAdapter.addData(data);
    }

    public void addData(T data) {
        mAdapter.addData(data);
    }

    public void addData(int index, T data) {
        mAdapter.addData(index, data);
    }



}
