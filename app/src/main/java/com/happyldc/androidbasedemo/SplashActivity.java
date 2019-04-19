package com.happyldc.androidbasedemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.happyldc.module.splash.BaseSplashActivity;

public class SplashActivity extends BaseSplashActivity {

    @Override
    protected int showTime() {
        return 1000;
    }

    @Override
    protected Object getContentViewLayoutOrLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void steupView(Bundle savedInstanceState) {

    }

    Toast toast;

    @Override
    protected void onTick(long millisUntilFinished) {
        if (toast == null) {
            toast=  Toast.makeText(this, millisUntilFinished+"", Toast.LENGTH_SHORT);
        }
        toast.setText("" + millisUntilFinished);
        toast.show();
    }

    @Override
    protected void onFinish() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
        finish();
    }
}
