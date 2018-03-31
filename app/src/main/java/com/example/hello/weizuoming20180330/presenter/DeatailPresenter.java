package com.example.hello.weizuoming20180330.presenter;


import com.example.hello.weizuoming20180330.model.DeatilModel;
import com.example.hello.weizuoming20180330.model.bean.DeatilBean;
import com.example.hello.weizuoming20180330.presenter.inter.DeatilPresenterInter;
import com.example.hello.weizuoming20180330.view.Iview.DetailActivityInter;

/**
 * Created by Dash on 2018/1/24.
 */
public class DeatailPresenter implements DeatilPresenterInter {

    private DeatilModel deatilModel;
    private DetailActivityInter detailActivityInter;

    public DeatailPresenter(DetailActivityInter detailActivityInter) {
        this.detailActivityInter = detailActivityInter;

        deatilModel = new DeatilModel(this);

    }

    public void getDetailData(String detailUrl, int pid) {

        deatilModel.getDetailData(detailUrl,pid);
    }

    @Override
    public void onSuccess(DeatilBean deatilBean) {
        //回调给view
        detailActivityInter.onSuccess(deatilBean);
    }
}
