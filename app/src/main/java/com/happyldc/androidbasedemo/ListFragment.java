package com.happyldc.androidbasedemo;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.happyldc.base.base.fragment.BaseListFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ldc
 * @Created at 2019/4/13 12:00.
 */

public class ListFragment extends BaseListFragment<UserEntity> {
    List<UserEntity> userList = new ArrayList<>();


    @Override
    protected void setupView(View contentView, Bundle savedInstanceState) {
        super.setupView(contentView, savedInstanceState);
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
        return new GridLayoutManager(getActivity(), 1);
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

//    @Override
//    protected RecyclerViewConfig createRecyclerViewConfig(View contentView) {
//        return new RecyclerViewConfig((RecyclerView) contentView.findViewById(R.id.recyclerview)
//                , new BaseQuickAdapter<UserEntity, BaseViewHolder>(android.R.layout.simple_list_item_1, new ArrayList<UserEntity>()) {
//            @Override
//            protected void convert(BaseViewHolder helper, UserEntity item) {
//                helper.setText(android.R.id.text1, item.id);
//            }
//
//        });
//    }
}
