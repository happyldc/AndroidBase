package com.happyldc.base.executor;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 磁盘IO执行者
 */
public class DiskIOThreadExecutor implements  Executor{

    private final Executor mDiskIO;

    public DiskIOThreadExecutor() {

        mDiskIO = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mDiskIO.execute(command);
    }
}
