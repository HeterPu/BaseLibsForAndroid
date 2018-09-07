package com.example.administrator.tabapplication.beans;

import android.view.View;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class HeaderFooterTypeBean implements MultiItemEntity {


    public static  final  int HEADER_TYPE = 1;
    public static  final  int BODY_TYPE = 2;
    public static  final  int FOOTER_TYPE = 2;

    /**
     * 数据的类型
     */
    private int type;


    public void setType(int type) {
        this.type = type;
    }


    @Override
    public int getItemType() {
        return type;
    }
}
