package com.example.administrator.tabapplication.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.tabapplication.R;
import com.example.administrator.tabapplication.beans.SimpleDataBean;

import java.util.List;

public class TestAdapter extends BaseQuickAdapter<SimpleDataBean, BaseViewHolder> {

    public TestAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, SimpleDataBean item) {
        helper.setText(R.id.itemtitle, item.getTitle())
                .setText(R.id.itemcontent, item.getContent()).setTag(R.id.itemtitle,"itemtitle").setTag(R.id.itemcontent,"itemcontent");
        ;
    }
}

