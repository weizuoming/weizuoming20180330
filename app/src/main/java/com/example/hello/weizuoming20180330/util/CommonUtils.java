package com.example.hello.weizuoming20180330.util;

import com.example.hello.weizuoming20180330.application.DashApplication;

/**
 * Created by 韦作铭 on 2018/3/30.
 */

public class CommonUtils {

    /**
     * 自己写的运行在主线程的方法
     * 如果是主线程,执行任务,否则使用handler发送到主线程中去执行
     *
     *
     * @param runable
     */
    public static void runOnUIThread(Runnable runable) {
        //先判断当前属于子线程还是主线程
        if (android.os.Process.myTid() == DashApplication.getMainThreadId()) {
            runable.run();
        } else {
            //子线程
            DashApplication.getAppHanler().post(runable);
        }
    }
}
