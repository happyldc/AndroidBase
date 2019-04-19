package com.happyldc.base.crash;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.happyldc.util.AppUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常日志处理
 * <p>1、APP启动时调用 {@link #register(Context)}捕获全局异常</p>
 * <p>2、创建一个默认接收到捕获异常提示的Activity</p>
 * <p>异常处理完成后会自动关闭程序 并隐式跳转到Action={@link #CRASH_RESTART_TIP_ACTION}的Activity，
 * 通过Intent.Extra相关信息
 * <ul>
 * <li>APK名称：{@link #CRASH_EXTRA_KEY_LOGCONTENT}</li>
 * <li>崩溃信息：{@link #CRASH_EXTRA_KEY_LOGCONTENT}</li>
 * </ul>
 * </p>
 * <p></p>
 *
 * @author ldc
 * @Created at 2018/3/13 10:07.
 */

public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {
    protected Context mContext;
    private StringBuffer mLogContent = new StringBuffer();
    private static String mCrashFileDirPath;
    private static GlobalExceptionHandler sInstance = new GlobalExceptionHandler();

    public final static String CRASH_RESTART_TIP_ACTION = "com.happyldc.crash.action.restart";

    public final static String CRASH_EXTRA_KEY_APPNAME = "crash_extra_key_appname";

    public final static String CRASH_EXTRA_KEY_LOGCONTENT = "crash_extra_key_log_content";

    public static GlobalExceptionHandler getInstance() {
        return sInstance;
    }

    Thread.UncaughtExceptionHandler mDefaultHandler;

    public void register(Context context) {
        this.mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        mCrashFileDirPath = context.getExternalCacheDir() + "/logs/";
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        if (!handleException(t, e) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处
            mDefaultHandler.uncaughtException(t, e);
        }
    }

    /**
     * app异常处理
     *
     * @param t 异常线程
     * @param e 异常日志
     * @return true 已处理 false移交给系统处理
     */
    protected boolean handleException(Thread t, Throwable e) {
        saveCrashInfo2LocalFile(t, e);
        e.printStackTrace();
        Intent intent = new Intent(CRASH_RESTART_TIP_ACTION);
        intent.putExtra(CRASH_EXTRA_KEY_APPNAME, AppUtil.getAppName(mContext.getPackageName()));
        intent.putExtra(CRASH_EXTRA_KEY_LOGCONTENT, mLogContent.toString());
        PendingIntent restartIntent = PendingIntent.getActivity(mContext, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
        AlarmManager mgr = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 60, restartIntent); // 1秒钟后重启应用

        //todo 销毁所有的Activity
        android.os.Process.killProcess(android.os.Process.myPid());
        return true;
    }


    /**
     * 保存异常信息到本地文件中
     *
     * @param thread
     * @param ex
     * @return 日志保存的路径
     */
    protected String saveCrashInfo2LocalFile(Thread thread, Throwable ex) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String time = formatter.format(new Date());
        long timestamp = System.currentTimeMillis();
        String fileName = "crashlog-" + time + "-" + timestamp + ".log";

        mLogContent.append(mCrashFileDirPath + fileName + "\n");
        mLogContent.append("APPName:" + AppUtil.getAppName(mContext.getPackageName()) + "\n");
        mLogContent.append("AppVersionCode:" + AppUtil.getAppVersionCode(mContext.getPackageName()) + "\n");
        mLogContent.append("AppVersionName:" + AppUtil.getAppVersionName(mContext.getPackageName()) + "\n");
        mLogContent.append("\n");
        mLogContent.append("ThrowableMessage:" + ex.getMessage() + "\n");
        mLogContent.append("CrashTime:" + time + "\n");

        mLogContent.append("\n\n");

        mLogContent.append(collectDeviceInfo().toString() + "\n\n");
        mLogContent.append(crashInfo(thread, ex).toString());

        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File dir = new File(mCrashFileDirPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(mCrashFileDirPath + fileName);
                fos.write(mLogContent.toString().getBytes());
                fos.close();
            }
            return fileName;
        } catch (Exception e) {
            Log.e(this.getClass().getSimpleName(), "an error occured while writing file...", e);
        }

        return null;
    }

    /**
     * 获取设备信息
     *
     * @return 设备所有信息
     */
    protected StringBuffer collectDeviceInfo() {
        Map<String, String> deviceInfo = new HashMap<>();

        /**
         * 通过反射的方法获得Build的所有域
         */
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                deviceInfo.put(field.getName(), field.get(null).toString());
            } catch (IllegalAccessException e) {

            }
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> enty : deviceInfo.entrySet()) {
            String key = enty.getKey();
            String value = enty.getValue().toString();
            sb.append(key + "=" + value + "\n");
        }
        return sb;
    }

    /**
     * 收集crash信息
     *
     * @return 完整的chrash信息
     */
    protected StringBuffer crashInfo(Thread thread, Throwable ex) {
        StringBuffer sb = new StringBuffer();
        long timeStamp = System.currentTimeMillis();

        sb.append("problem appears at thread:").append(thread.getName() + ",its ID:" + thread.getId() + "\n");
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        return sb;
    }


}
