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


    @Override
    protected void configuration() {
        super.configuration();
//        setStatusBarColor(Color.RED);
    }


    @Override
    protected boolean getIsTitleCenterInParent() {
        return false;
    }

    @Override
    protected int getMainLayoutId() {
       return R.layout.testjumpactivity_layout;
    }


//        @Override
//    protected boolean hideNavigationBar() {
//        return true;
//    }

    @Override
    protected int getNaviBarBackgroundColor() {
        return Color.BLACK;
    }

//    @Override
//    protected boolean isFullScreenMode() {
//        return true;
//    }

    @Override
    protected boolean preferStatusBarStyle() {
        return false;
    }




//    @Override
//    protected boolean isPresentedActivity() {
//        return true;
//    }
}

