package com.example.hello.weizuoming20180330.view.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hello.weizuoming20180330.R;
import com.example.hello.weizuoming20180330.model.bean.MyBean;
import com.example.hello.weizuoming20180330.model.bean.MyJieSuan;
import com.example.hello.weizuoming20180330.presenter.Presenter;
import com.example.hello.weizuoming20180330.util.RetrofitUtil;
import com.example.hello.weizuoming20180330.util.Url;
import com.example.hello.weizuoming20180330.view.holder.MyChaXunHolder;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by 韦作铭 on 2018/3/30.
 */

public class MyChaXunAdapter extends RecyclerView.Adapter<MyChaXunHolder> {
    private final Context context;
    private final MyBean myBean;
    private final Presenter presenter;
    private final Handler handler;
    private int conindex;
    private MyChildAdapter adapter;


    public MyChaXunAdapter(Context context, MyBean myBean, Presenter presenter, Handler handler) {

        this.context = context;
        this.myBean = myBean;
        this.presenter = presenter;
        this.handler = handler;
    }

    @Override
    public MyChaXunHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R
                .layout.item_group, parent, false);
        MyChaXunHolder holder = new MyChaXunHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyChaXunHolder holder, final int position) {
        final List<MyBean.DataBean> data = myBean.getData();
        holder.che_group.setChecked(data.get(position).isCheckbox());
        holder.text_group.setText(data.get(position).getSellerName());
        holder.che_group.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                conindex = 0;
                allChildChecked(holder.che_group.isChecked(),data.get(position));
            }

        });
        holder.recycler_child.setLayoutManager(new LinearLayoutManager(context
                ,LinearLayoutManager.VERTICAL,false));
        adapter = new MyChildAdapter(context, myBean, handler, presenter,position);
        holder.recycler_child.setAdapter(adapter);

    }

    private void allChildChecked(final boolean checked, final MyBean.DataBean dataBean) {
        final MyBean.DataBean.ListBean bean = dataBean.getList().get(conindex);

        Map<String, String> params = new HashMap<>();
        params.put("uid", "72");
        params.put("sellerid", String.valueOf(bean.getSellerid()));
        params.put("pid", String.valueOf(bean.getPid()));
        params.put("num", String.valueOf(bean.getNum()));
        params.put("selected", String.valueOf(checked ? 1 : 0));

        RetrofitUtil.gery().doGet(Url.gengxin,params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody value) {
                        conindex++;
                        if (conindex<dataBean.getList().size()){
                            allChildChecked(checked,dataBean);
                        }else {
                            presenter.getUrl(Url.chaxun);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }



    @Override
    public int getItemCount() {
        return myBean.getData().size();
    }

    public void setNumSumAll() {

        double price=0;
        int num=0;
        List<MyBean.DataBean> data = myBean.getData();
        for (int i=0;i<data.size();i++){
            List<MyBean.DataBean.ListBean> beans = data.get(i).getList();
            for (int j=0;j<beans.size();j++){
                if (beans.get(j).getSelected()==1){
                    price+=beans.get(j).getBargainPrice()*beans.get(j).getNum();
                    num+=beans.get(j).getNum();
                }
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String format = decimalFormat.format(price);
        MyJieSuan myHeJiBean = new MyJieSuan(format, num);
        Message msg = Message.obtain();
        msg.what=0;
        msg.obj=myHeJiBean;
        handler.sendMessage(msg);
    }

    public void allChed(boolean checked) {
        adapter.allChed(checked);

    }

}
