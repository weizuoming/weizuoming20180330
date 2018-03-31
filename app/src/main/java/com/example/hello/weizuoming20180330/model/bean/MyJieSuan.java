package com.example.hello.weizuoming20180330.model.bean;

/**
 * Created by 韦作铭 on 2018/3/19.
 */

public class MyJieSuan {
    private String price;
    private int num;

    public MyJieSuan(String price, int num) {
        this.price = price;
        this.num = num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
