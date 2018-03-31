package com.example.hello.weizuoming20180330.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.hello.weizuoming20180330.R;
import com.example.hello.weizuoming20180330.model.bean.MyBean;
import com.example.hello.weizuoming20180330.model.bean.MyJieSuan;
import com.example.hello.weizuoming20180330.presenter.Presenter;
import com.example.hello.weizuoming20180330.util.Url;
import com.example.hello.weizuoming20180330.view.adapter.MyChaXunAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import butterknife.OnClick;
import okhttp3.ResponseBody;

public class Main2Activity extends AppCompatActivity implements MyView{
    private Presenter presenter;
    private int num;
    private String price;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                MyJieSuan myHeJiBean= (MyJieSuan) msg.obj;
                sumAll.setText("合计:¥"+myHeJiBean.getPrice());
                numAll.setText("去结算("+myHeJiBean.getNum()+")");
                num = myHeJiBean.getNum();
                price = myHeJiBean.getPrice();
            }
        }
    };

    private MyChaXunAdapter adapter;
    private RecyclerView recyclerView;
    private CheckBox cheAll;
    private TextView numAll;
    private TextView sumAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = findViewById(R.id.recycler_view);
        cheAll = findViewById(R.id.che_all);
        numAll = findViewById(R.id.num_all);
        sumAll = findViewById(R.id.sum_all);
        //   ButterKnife.bind(getActivity());
        presenter = new Presenter(this);
    }
    private boolean isChild(List<MyBean.DataBean> list) {
        for (int i=0;i<list.size();i++){
            if (!list.get(i).isCheckbox()){
                return false;
            }
        }
        return true;
    }

    private boolean ischecked(int i, List<MyBean.DataBean> list) {
        for (int j=0;j<list.get(i).getList().size();j++){
            if (list.get(i).getList().get(j).getSelected()==0){
                return false;
            }
        }
        return true;
    }
    @Override
    public void getReView(ResponseBody responseBody) {
        try {
            MyBean myBean = new Gson().fromJson(responseBody.string(), MyBean.class);
            List<MyBean.DataBean> list = myBean.getData();
            //二级列表全部选中一级列表选中
            for (int i=0;i<list.size();i++){
                if (ischecked(i,list)){
                    list.get(i).setCheckbox(true);
                }
            }
            cheAll.setChecked(isChild(list));
            recyclerView.setLayoutManager(new LinearLayoutManager(Main2Activity.this
                    ,LinearLayoutManager.VERTICAL,false));
            adapter = new MyChaXunAdapter(Main2Activity.this, myBean, presenter, handler);

            recyclerView.setAdapter(adapter);
            adapter.setNumSumAll();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getUrl(Url.chaxun);
        presenter.attachView(this);
    }




    @Override
    public void getError(Throwable throwable) {

    }


    @OnClick({R.id.che_all, R.id.num_all})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.che_all:
                if (adapter!=null){
                    adapter.allChed(cheAll.isChecked());
                }
                break;
            case R.id.num_all:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.jiebang();
        if (presenter!=null){
            presenter.dettach();
        }
    }
}
