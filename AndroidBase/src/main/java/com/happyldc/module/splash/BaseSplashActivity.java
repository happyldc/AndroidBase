package com.happyldc.module.splash;

import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.happyldc.base.base.activity.BaseActivity;


public abstract class BaseSplashActivity extends BaseActivity {
    private static final String TAG = "BaseSplashActivity";
    private CountDownTimer mCountDownTimer;

    @Override
    protected void beforeSetContetView(Bundle savedInstanceState) {
        super.beforeSetContetView(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mCountDownTimer = new CountDownTimer(showTime(), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                BaseSplashActivity.this.onTick(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                BaseSplashActivity.this.onFinish();
            }
        };
        mCountDownTimer.start();
    }

    /***
     * 设置页面停留时间 单位S
     * @return
     */
    protected abstract int showTime();

    /***
     * 需要调用setContentView设置显示的布局s
     */
    protected abstract void steupView(Bundle savedInstanceState);

    /**
     * 每秒调用一次
     *
     * @param millisUntilFinished
     */
    protected abstract void onTick(long millisUntilFinished);

    /***
     * 到达页面停留时间
     */
    protected abstract void onFinish();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }
}
