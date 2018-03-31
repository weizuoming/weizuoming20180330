package com.example.hello.weizuoming20180330.model;


import com.example.hello.weizuoming20180330.presenter.inter.PresenterPort;
import com.example.hello.weizuoming20180330.util.RetrofitUtil;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by 韦作铭 on 2018/3/19.
 */

public class Molder {
    private Disposable d;
    private PresenterPort presenterPort;

    public Molder(PresenterPort presenterPort) {
        this.presenterPort = presenterPort;
    }

    public void getUrl(String chaxun) {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid","72");
        map.put("source","android");
        RetrofitUtil.gery().doGet(chaxun,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {


                    @Override
                    public void onSubscribe(Disposable d) {

                        Molder.this.d = d;
                    }

                    @Override
                    public void onNext(ResponseBody value) {
                        presenterPort.getReView(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void jiebang() {
        d.dispose();
    }
}
