package com.happyldc.base.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * APP线程执行者 用于替换手动创建的Thread
 */
public class AppExecutors {

    private static final int THREAD_COUNT = 3;

    Executor mMainThreadExecutor;
    Executor mDiskIOThreadExecutor;
    Executor mNetworkIOThreadExecutor;

    public AppExecutors() {
        mMainThreadExecutor = new MainThreadExecutor();
        mDiskIOThreadExecutor = new DiskIOThreadExecutor();
        mNetworkIOThreadExecutor = Executors.newFixedThreadPool(THREAD_COUNT);
        
    }

    public Executor mainThread() {
        return mMainThreadExecutor;
    }

    public Executor diskIO() {
        return mDiskIOThreadExecutor;
    }

    public Executor networkIO() {
        return mNetworkIOThreadExecutor;
    }
}
