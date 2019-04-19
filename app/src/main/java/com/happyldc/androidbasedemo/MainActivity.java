package com.happyldc.androidbasedemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.happyldc.base.base.activity.BaseActivity;

import java.util.Random;

public class MainActivity extends BaseActivity {


    @Override
    protected Object getContentViewLayoutOrLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void steupView(Bundle savedInstanceState) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new ListFragment())
                .commit();
    }

    @SuppressLint("MissingPermission")
    public void setting(View view) {
        // throw  new IllegalStateException("参数错误");

        go();
//        Intent intent=new Intent("com.happyldc.crash.action.restart");
////        intent.setAction();
////        Intent intent=new Intent(this, ExceptionDialogActivity.class);
//        startActivity(intent);
//        startActivity(IntentUtils.getAppDetailsSettingIntent("com.lbm.work", false));
//        startActivity(IntentUtils.getDevelopmentSettingIntent( false));

        //  startActivity(IntentUtils.getCallPhoneIntent("15059611029", false));

    }

    @Override
    protected void onRetryLoadClick() {
        super.onRetryLoadClick();
        go();
    }

    void go() {
        loadingHelper.showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int i = new Random().nextInt(9);
                        if (i <= 3) {
                            loadingHelper.showLoadSuccess();
                            Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();

                        } else {
                            loadingHelper.showLoadFailed();
                            Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        }).start();
//        PermissionHelper.with(this)
//                .request(Manifest.permission.CALL_PHONE, new WrapApplyResultCallback() {
//                    @SuppressLint("MissingPermission")
//                    @Override
//                    public void onFullGranted(int requestCode, String[] granteds) {
//                        loadingHelper.showLoadSuccess();
//                    }
//
//
//                    @Override
//                    protected void onDenieds(int requestCode, String[] granteds, String[] denieds) {
//                        loadingHelper.showLoadFailed();
//                    }
//                });
    }
}
