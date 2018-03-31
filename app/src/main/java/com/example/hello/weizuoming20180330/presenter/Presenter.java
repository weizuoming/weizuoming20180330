package com.example.hello.weizuoming20180330.presenter;


import com.example.hello.weizuoming20180330.model.Molder;
import com.example.hello.weizuoming20180330.presenter.inter.PresenterPort;
import com.example.hello.weizuoming20180330.view.MyView;

import okhttp3.ResponseBody;

/**
 * Created by 韦作铭 on 2018/3/19.
 */

public class Presenter extends BasePresenter implements PresenterPort {

    private MyView myView;
    private final Molder molder;

    public Presenter(MyView myView) {
        molder = new Molder(this);
        this.myView = myView;
    }

    @Override
    public void getReView(ResponseBody responseBody) {
        myView.getReView(responseBody);
    }

    @Override
    public void getError(Throwable throwable) {
        myView.getError(throwable);
    }

    public void getUrl(String chaxun) {
        molder.getUrl(chaxun);
    }

    public void jiebang() {
        molder.jiebang();
    }
}
