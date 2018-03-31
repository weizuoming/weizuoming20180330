package com.example.hello.weizuoming20180330.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hello.weizuoming20180330.R;


/**
 * Created by 韦作铭 on 2018/3/29.
 */
public class TuiJianHolder extends RecyclerView.ViewHolder {

    public ImageView tui_jian_image;
    public TextView tui_jian_item_title;
    public TextView tui_jian_item_price;

    public TuiJianHolder(View itemView) {
        super(itemView);

        tui_jian_image = itemView.findViewById(R.id.tui_jian_item_image);
        tui_jian_item_title = itemView.findViewById(R.id.tui_jian_item_title);
        tui_jian_item_price = itemView.findViewById(R.id.tui_jian_item_price);

    }
}
