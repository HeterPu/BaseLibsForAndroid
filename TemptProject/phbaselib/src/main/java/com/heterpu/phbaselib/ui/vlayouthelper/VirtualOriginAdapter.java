package com.heterpu.phbaselib.ui.vlayouthelper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;


/**
 *  For vLayout origin adapter
 */
public class VirtualOriginAdapter extends DelegateAdapter.Adapter<VirtualOriginAdapter.MainViewHolder> {


    private Context mContext;

    private LayoutHelper mLayoutHelper;
    private VirtualLayoutManager.LayoutParams mLayoutParams;
    private int mCount = 0;


    public VirtualOriginAdapter(Context context, LayoutHelper layoutHelper) {
        this(context, layoutHelper, layoutHelper.getItemCount(), new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
    }

    public VirtualOriginAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
        this.mContext = context;
        this.mLayoutHelper = layoutHelper;
        this.mCount = count;
        this.mLayoutParams = layoutParams;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(mContext).inflate(getResourceIdWithViewType(viewType), parent, false));
    }


    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        // only vertical
        convert(holder.itemView,position);
    }


    @Override
    protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {

    }


    @Override
    public int getItemCount() {
        return mCount;
    }



    protected int getResourceIdWithViewType(int viewType){
        return 0;
    }


    protected void convert(View parent, int position){

    }


    public static class MainViewHolder extends RecyclerView.ViewHolder {

        public static volatile int existing = 0;
        public static int createdTimes = 0;

        public MainViewHolder(View itemView) {
            super(itemView);
            createdTimes++;
            existing++;
        }

        @Override
        protected void finalize() throws Throwable {
            existing--;
            super.finalize();
        }
    }
}

