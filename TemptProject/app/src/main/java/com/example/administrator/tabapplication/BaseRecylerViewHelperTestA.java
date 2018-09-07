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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.BaseAnimation;

import com.example.administrator.tabapplication.adapter.TestAdapter;
import com.example.administrator.tabapplication.beans.HeaderFooterTypeBean;
import com.example.administrator.tabapplication.beans.SimpleDataBean;
import com.github.magiepooh.recycleritemdecoration.ItemDecorations;

import java.util.ArrayList;
import java.util.EventListener;
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
