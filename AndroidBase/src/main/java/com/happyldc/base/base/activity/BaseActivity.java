package com.happyldc.base.base.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.happyldc.base.loading.LoadingHelper;

/**
 * 应用所有的Activity均要继承本类
 *
 * @author ldc
 * @Created at 2019/4/12 17:30.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected LoadingHelper loadingHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetContetView(savedInstanceState);
        Object obj = getContentViewLayoutOrLayoutId();
        if (obj == null) {
            throw new IllegalArgumentException("getContentViewLayoutOrLayoutId  Cannot return null.");
        }
        if (obj instanceof View) {
            setContentView((View) obj);
        } else if (obj instanceof Integer) {
           setContentView((int) obj);
        } else {
            throw new IllegalArgumentException("getContentViewLayoutOrLayoutId Only view or layoutId can be returned.");
        }
        loadingHelper = new LoadingHelper(this, new Runnable() {
            @Override
            public void run() {
                onRetryLoadClick();
            }
        });
        steupView(savedInstanceState);
    }

    protected void beforeSetContetView(Bundle savedInstanceState) {
    }

    /***
     * 返回contentView 的layoutId 或者ContentView
     * @return
     */
    protected abstract Object getContentViewLayoutOrLayoutId();

    protected abstract void steupView(Bundle savedInstanceState);

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            return onNavigationClick();
        }
        return super.onOptionsItemSelected(item);
    }


    protected void onRetryLoadClick() {

    }

    /**
     * 导航栏点击事件 默认关闭当前Activity
     *
     * @return
     */
    protected boolean onNavigationClick() {
        finish();
        return true;
    }

}
