package com.example.administrator.tabapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.android.vlayout.layout.FixAreaLayoutHelper;
import com.heterpu.phbaselib.ui.activity.BaseActivity;

public class TestJumpAndPresentedActivity extends BaseApplicationActivity {

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.testjumpactivity_layout);
//
//    }
//


//    @Override
//    public boolean isSupportSwipeBack() {
//        return true;
//    }

    @Override
    protected void configuration() {
        super.configuration();
        setTitle("我爱河东狮");
//        setStatusBarColor(Color.RED);
    }

    @Override
    protected int getMainLayoutId() {
       return R.layout.testjumpactivity_layout;
    }


    //    @Override
//    protected boolean hideNavigationBar() {
//        return true;
//    }
//
//    @Override
//    protected boolean isPresentedActivity() {
//        return true;
//    }


    @Override
    protected int getNaviBarBackgroundColor() {
        return Color.BLACK;
    }
}

