package com.example.hello.weizuoming20180330.util;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 韦作铭 on 2018/3/30.
 */

public class RetrofitUtil {
    private static RetrofitUtil retrofitUtil=null;
    private Retrofit retrofit;

    private RetrofitUtil(){

    }
    private RetrofitUtil(String baseurl){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static RetrofitUtil getintfice(String baseurl){
        if (retrofitUtil==null){
            synchronized (RetrofitUtil.class){
                if (retrofitUtil==null){
                    retrofitUtil=new RetrofitUtil(baseurl);
                }
            }
        }
        return retrofitUtil;
    }

    public <T> T createservice(Class<T> service){

        return retrofit.create(service);
    }

    public static ApiUtil gery(){
        return RetrofitUtil.getintfice(com.example.hello.weizuoming20180330.util.Url.ent).createservice(ApiUtil.class);
    }
}
