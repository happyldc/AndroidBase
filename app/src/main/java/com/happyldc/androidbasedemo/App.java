package com.happyldc.androidbasedemo;

import android.app.Application;

import com.billy.android.loading.Gloading;
import com.happyldc.base.crash.GlobalExceptionHandler;
import com.happyldc.base.loading.GlobalAdapter;
import com.happyldc.util.Utils;


/**
 * @author ldc
 * @Created at 2019/4/12 11:35.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        GlobalExceptionHandler.getInstance().register(this);
        Gloading.debug(true);
        Gloading.initDefault(new GlobalAdapter());
    }
}
