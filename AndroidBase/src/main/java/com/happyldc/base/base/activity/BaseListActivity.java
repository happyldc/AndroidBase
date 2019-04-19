package com.happyldc.base.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.happyldc.base.R;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListActivity<T> extends BaseActivity {

    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager layoutManager;

    protected BaseQuickAdapter<T, BaseViewHolder> mAdapter;

    @Override
    protected Object getContentViewLayoutOrLayoutId() {
        return R.layout.base_activity_list;
    }

    @Override
    protected void steupView(Bundle savedInstanceState) {
        initView(savedInstanceState);
        this.layoutManager = createLayoutManager();
        this.mAdapter = createAdapter();
        this.mRecyclerView.setLayoutManager(this.layoutManager);
        this.mRecyclerView.setAdapter(this.mAdapter);
    }


    public abstract RecyclerView.LayoutManager createLayoutManager();

    public abstract BaseQuickAdapter<T, BaseViewHolder> createAdapter();

    public BaseQuickAdapter<T, BaseViewHolder> getAdapter() {
        return mAdapter;
    }
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }
    private void initView(Bundle savedInstanceState) {
        mRecyclerView = findViewById(R.id.base_recyclerview);
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
