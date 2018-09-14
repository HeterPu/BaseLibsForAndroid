package com.example.administrator.tabapplication;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.BaseAnimation;

import com.example.administrator.tabapplication.adapter.TestAdapter;
import com.example.administrator.tabapplication.beans.HeaderFooterTypeBean;
import com.example.administrator.tabapplication.beans.SimpleDataBean;
import com.github.magiepooh.recycleritemdecoration.ItemDecorations;
import com.heterpu.phbaselib.ui.uicollectionview.UICol_Beans;
import com.heterpu.phbaselib.ui.vlayouthelper.VirtualBean;
import com.heterpu.phbaselib.ui.vlayouthelper.VirtualHelperAdapter;
import com.heterpu.phbaselib.ui.vlayouthelper.VirtualOriginAdapter;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.LinkedList;
import java.util.List;

public class BaseRecylerViewHelperTestA extends AppCompatActivity {


    private List<SimpleDataBean> dataArra;
    private List<HeaderFooterTypeBean> typearra;

    private RecyclerView recyclerView;
    private TestAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_recyler_view_helper_test);
        recyclerView = findViewById(R.id.recyclerView);
        vLayoutDemoWithOrigin();

    }





    private  void  vLayoutDemoWithOrigin(){
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        // recyclerView.addItemDecoration(itemDecoration);

        viewPool.setMaxRecycledViews(0, 20);

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager,true);
        recyclerView.setAdapter(delegateAdapter);

        List<DelegateAdapter.Adapter> adpters = new LinkedList<>();




        final int type1 = 1;
        final int type2 = 2;

        List<VirtualBean> virtualBeans1 = new ArrayList<>();
        List<VirtualBean> virtualBeans2 = new ArrayList<>();


        for (int i = 0;i < 6;i++){
            VirtualBean bean = new VirtualBean(type1);
            virtualBeans1.add(bean);
        }

        for (int i = 0;i < 16;i++){
            VirtualBean bean = new VirtualBean(type2);
            virtualBeans2.add(bean);
        }



        GridLayoutHelper gridHelper1 = new GridLayoutHelper(3);
        gridHelper1.setItemCount(virtualBeans1.size());
        gridHelper1.setMargin(10,10,10,10);
        gridHelper1.setGap(20);

        adpters.add(new VirtualOriginAdapter(this,gridHelper1){
            @Override
            public int getItemViewType(int position) {
                return type1;
            }


            @Override
            protected int getResourceIdWithViewType(int viewType) {
              return   R.layout.simpledata_layout;
            }

        });





        GridLayoutHelper gridHelper2 = new GridLayoutHelper(4);
        gridHelper2.setItemCount(virtualBeans2.size());
        gridHelper2.setGap(30);
        adpters.add(new VirtualOriginAdapter(this,gridHelper2){
            @Override
            public int getItemViewType(int position) {
                if (position > 7)return type1;
                return type2;
            }


            @Override
            protected int getResourceIdWithViewType(int viewType) {
                if (viewType == type1)return R.layout.simpledata_layout;
                return R.layout.item_test;
            }


            @Override
            protected void convert(View parent, int position) {

            }
        });

        delegateAdapter.addAdapters(adpters);
    }



    private void vLayoutDemo(){


        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        final int type1 = 1;
        final int type2 = 2;

        List<VirtualBean> virtualBeans = new ArrayList<>();
        List<VirtualBean> virtualBeans1 = new ArrayList<>();
        List<VirtualBean> virtualBeans2 = new ArrayList<>();


        for (int i = 0;i < 8;i++){
            VirtualBean bean = new VirtualBean(type1);
            virtualBeans1.add(bean);
        }

        for (int i = 0;i < 18;i++){
            VirtualBean bean = new VirtualBean(type2);
            virtualBeans1.add(bean);
        }

        virtualBeans.addAll(virtualBeans1);
        virtualBeans.addAll(virtualBeans2);

        List<LayoutHelper> helpers = new LinkedList<>();

        GridLayoutHelper gridHelper1 = new GridLayoutHelper(2);
        gridHelper1.setItemCount(virtualBeans1.size());
        gridHelper1.setMargin(10,10,10,10);
        gridHelper1.setGap(20);
        helpers.add(gridHelper1);




        GridLayoutHelper gridHelper2 = new GridLayoutHelper(4);
        gridHelper2.setItemCount(virtualBeans2.size());
        gridHelper2.setGap(30);
        helpers.add(gridHelper2);

        layoutManager.setLayoutHelpers(helpers);



        VirtualHelperAdapter<VirtualBean,BaseViewHolder> helper = new VirtualHelperAdapter<VirtualBean, BaseViewHolder>(layoutManager,virtualBeans) {
            @Override
            protected void convert(BaseViewHolder helper, VirtualBean item) {
                Log.e("","");
            }

            @Override
            public List<UICol_Beans.ResourceBind> getResourceBind() {
                List<UICol_Beans.ResourceBind> beans = new ArrayList<>();
                UICol_Beans.ResourceBind bean1 = new UICol_Beans.ResourceBind(type1,R.layout.simpledata_layout);


                UICol_Beans.ResourceBind bean2 = new UICol_Beans.ResourceBind(type2,R.layout.item_test);

                beans.add(bean1);
                beans.add(bean2);
                return beans;
            }
        };
        recyclerView.setAdapter(helper);
    }






    private void  demo1(){
        setDataArra();
        adapter = new TestAdapter(R.layout.simpledata_layout,dataArra);
        recyclerView.setLayoutManager(new GridLayoutManager(this,6));
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        },recyclerView);



        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(BaseRecylerViewHelperTestA.this,"hello world" + "position is " + position + position + view.getTag()
                        ,Toast.LENGTH_LONG).show();
            }
        });

        adapter.openLoadAnimation(new BaseAnimation() {
            @Override
            public Animator[] getAnimators(View view) {
                return new Animator[]{ObjectAnimator.ofFloat(view,"scaleY",1,1.3f,1),ObjectAnimator.ofFloat(view,"scaleX",1,1.2f,1)};
            }
        });
        adapter.isFirstOnly(false);

        View headerV = new View(this);
        headerV.setBackgroundColor(Color.CYAN);

        View footer = new View(this);
        footer.setBackgroundColor(Color.BLUE);

        ViewGroup.LayoutParams headerParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200);
        ViewGroup.LayoutParams footerParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100);
        headerV.setLayoutParams(headerParam);
        footer.setLayoutParams(footerParam);

        adapter.setHeaderView(headerV);
//        adapter.setFooterView(footer);

        adapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            /**
             * @param gridLayoutManager
             * @param position
             * @return
             */
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {

                int itemType =  typearra.get(position).getItemType();
                if (position % 2 == 0){
                    return 4;
                }else {
                    return 2;
                }
            }
        });


        RecyclerView.ItemDecoration decoration = ItemDecorations.vertical(this).first(R.mipmap.addteam).last(R.mipmap.ic_launcher).create();

//        GridDividerItemDecoration dividerItemDecoration = new GridDividerItemDecoration(this,
//                GridDividerItemDecoration.GRID_DIVIDER_VERTICAL);
//        GridDividerItemDecoration dividerItemDecoration_H = new GridDividerItemDecoration(this,
//                GridDividerItemDecoration.GRID_DIVIDER_HORIZONTAL);
//        dividerItemDecoration.setVerticalDivider(ver);
//        dividerItemDecoration.setHorizontalDivider(horizontalDivider);
////        ShaderItemDecoration shaderItemDecoration = new ShaderItemDecoration(this,ShaderItemDecoration.SHADER_TOP);
////        shaderItemDecoration.setShaderTopDistance(80);
//
//        LinearDividerItemDecoration divideDecoration = new LinearDividerItemDecoration(this, app.dinus.com.itemdecoration.LinearDividerItemDecoration.LINEAR_DIVIDER_HORIZONTAL);
//
//
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                RecyclerView.LayoutManager manager = parent.getLayoutManager();
                int itemCount = parent.getAdapter().getItemCount();
                int childrenPosition = parent.getChildAdapterPosition(view);
                if(childrenPosition == 0){
                    outRect.set(0,0,0,0);
                }else {
                    if (childrenPosition % 2 == 1){
                        outRect.set(10, 10, 10, 0);
                    }else {
                        outRect.set(0, 10, 10, 0);
                    }
                }
            }
        });

    }






    private void setDataArra(){
        dataArra = new ArrayList<>();
        typearra = new ArrayList<>();
        for (int i = 0;i < 20; i++){
            SimpleDataBean data = new SimpleDataBean();
            data.setTitle("title" + i);
            data.setContent("content" + i);
            dataArra.add(data);

            HeaderFooterTypeBean typeItem = new HeaderFooterTypeBean();

            if(i == 0){
                typeItem.setType(HeaderFooterTypeBean.HEADER_TYPE);
            }else if(i == 19){
                typeItem.setType(HeaderFooterTypeBean.FOOTER_TYPE);
            }else{
                typeItem.setType(HeaderFooterTypeBean.BODY_TYPE);
            }

            typearra.add(typeItem);
        }
    }



}
