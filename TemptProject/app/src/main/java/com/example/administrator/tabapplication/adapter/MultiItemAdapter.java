package com.example.administrator.tabapplication.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.tabapplication.beans.HeaderFooterTypeBean;

import java.util.List;

public class MultiItemAdapter extends BaseMultiItemQuickAdapter<HeaderFooterTypeBean,BaseViewHolder> {



    public MultiItemAdapter(@Nullable List<HeaderFooterTypeBean> data) {
        super(data);
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    protected void convert(BaseViewHolder helper, HeaderFooterTypeBean item) {

    }

}
