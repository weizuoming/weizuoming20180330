package com.example.hello.weizuoming20180330.view;

import okhttp3.ResponseBody;

/**
 * Created by 韦作铭 on 2018/3/30.
 */

public interface MyView {
    void getReView(ResponseBody responseBody);
    void getError(Throwable throwable);
}
