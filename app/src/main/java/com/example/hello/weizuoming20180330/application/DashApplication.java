package com.example.hello.weizuoming20180330.application;

import android.app.Application;
import android.os.Handler;
import android.os.Process;

/**
 * Created by 韦作铭 on 2018/3/29.
 */
public class DashApplication extends Application{

    private static int myTid;
    private static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();

        myTid = Process.myTid();
        handler = new Handler();
    }

    //获取主线程的id
    public static int getMainThreadId() {

        return myTid;
    }

    //获取handler
    public static Handler getAppHanler() {
        return handler;
    }
}
