package com.happyldc.androidbasedemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.happyldc.base.base.activity.BaseListActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends BaseListActivity<UserEntity> {
    List<UserEntity> userList = new ArrayList<>();


    @Override
    protected void steupView(Bundle savedInstanceState) {
        super.steupView(savedInstanceState);
        getData();


    }

    private void getData() {
        loadingHelper.showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    @Override
    protected void onRetryLoadClick() {
        super.onRetryLoadClick();
        getData();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (new Random().nextInt(10) > 7) {
                for (int i = 0; i < 20; i++) {
                    UserEntity userEntity = new UserEntity();
                    userEntity.id = i + "0000";
                    userList.add(userEntity);
                }
                loadingHelper.showLoadSuccess();
                setData(userList);

            } else {
                loadingHelper.showLoadFailed();
            }
        }
    };
    @Override
    public RecyclerView.LayoutManager createLayoutManager() {
        return new GridLayoutManager(getBaseContext(), 1);
    }

    @Override
    public BaseQuickAdapter<UserEntity, BaseViewHolder> createAdapter() {
        return new BaseQuickAdapter<UserEntity, BaseViewHolder>(android.R.layout.simple_list_item_1, new ArrayList<UserEntity>()) {
            @Override
            protected void convert(BaseViewHolder helper, UserEntity item) {
                helper.setText(android.R.id.text1, item.id);
            }
        };
    }


}
