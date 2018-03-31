package com.example.hello.weizuoming20180330.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by 韦作铭 on 2018/3/19.
 */

public abstract class BasePresenter<T> {
    public Reference<T> reference;
    public void attachView(T mview){
        reference = new WeakReference<>(mview);
    }
    public T getView(){
        if (reference!=null) {
            return reference.get();
        }
        return null;
    }

    public boolean isViewAttached() {//判断是否与View建立了关联
        return reference != null && reference.get() != null;
    }
    public void dettach(){

        if (reference!=null){
            reference.clear();
            reference=null;
        }
    }
}
