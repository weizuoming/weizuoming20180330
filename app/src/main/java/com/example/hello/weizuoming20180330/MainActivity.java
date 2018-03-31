package com.example.hello.weizuoming20180330;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.example.hello.weizuoming20180330.model.bean.FenLeiBean;
import com.example.hello.weizuoming20180330.model.bean.HomeBean;
import com.example.hello.weizuoming20180330.presenter.FragmentHomeP;
import com.example.hello.weizuoming20180330.util.Url;
import com.example.hello.weizuoming20180330.view.Iview.InterFragmentHome;
import com.example.hello.weizuoming20180330.view.Iview.OnItemListner;
import com.example.hello.weizuoming20180330.view.Main2Activity;
import com.example.hello.weizuoming20180330.view.adapter.TuiJianAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements InterFragmentHome {

    private FragmentHomeP fragmentHomeP;


    private RecyclerView tui_jian_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tui_jian_recycler = findViewById(R.id.tui_jian_recycler);
        //在这里可以做其他操作....
        fragmentHomeP = new FragmentHomeP(this);

        //调用p层里面的方法....https://www.zhaoapi.cn/ad/getAd
        fragmentHomeP.getNetData(Url.HOME_URL);

        //一个view一般是对应着一个presenter和一个model;;;;逻辑比较复杂的页面可能会对应多个presenter和多个model
        TextView qing=findViewById(R.id.qing);
        qing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });
    }


    @Override
    public void onSuccess(final HomeBean homeBean) {
        //设置显示bannner
        List<HomeBean.DataBean> datas = homeBean.getData();

        List<String> imageUrls = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            imageUrls.add(datas.get(i).getIcon());
        }
        //瀑布流
        tui_jian_recycler.setLayoutManager(new StaggeredGridLayoutManager(1, OrientationHelper.VERTICAL));
        //为你推荐设置适配器
        TuiJianAdapter tuiJianAdapter = new TuiJianAdapter(MainActivity.this, homeBean.getTuijian());
        tui_jian_recycler.setAdapter(tuiJianAdapter);

        //为你推荐的点击事件
        tuiJianAdapter.setOnItemListner(new OnItemListner() {
            @Override
            public void onItemClick(int position) {

                //跳转的是自己的详情页面
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                //传递pid
                intent.putExtra("pid",homeBean.getTuijian().getList().get(position).getPid());
                startActivity(intent);

            }

            @Override
            public void onItemLongClick(int position) {

            }
        });

    }


    @Override
    public void onFenLeiDataSuccess(FenLeiBean fenLeiBean) {

    }

}
