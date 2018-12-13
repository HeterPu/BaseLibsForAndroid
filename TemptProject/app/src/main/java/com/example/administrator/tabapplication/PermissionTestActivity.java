package com.example.administrator.tabapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.heterpu.phbaselib.ui.module.permission.MainActivityPermissionDispatcher;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PermissionTestActivity extends BaseApplicationActivity {




    @BindView(R.id.button)
    Button mButton;

    @BindView(R.id.button2)
    Button mButton2;




    @Override
    protected void configuration() {
        super.configuration();


        //设置toolbar


        setTitle("哈啊啊");

        //菜单点击事件（注意需要在setSupportActionBar(toolbar)之后才有效果）

        mNaviBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return true;
            }
        });


//        setSupportActionBar(mNaviBar);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDevicePermissionWith(MainActivityPermissionDispatcher.REQUEST_READWRITECONTACTS, new PermissionCallBack() {
                    @Override
                    public boolean allowed(int requestCode) {
                        return false;
                    }

                    @Override
                    public boolean denied(int requestCode) {
                        return false;
                    }

                    @Override
                    public boolean rationale(PermissionRequestion request, int requestCode) {
                        return false;
                    }

                    @Override
                    public boolean neverask(int requestCode) {
                        return false;
                    }
                });
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PermissionTestActivity.this,TestJumpAndPresentedActivity.class);
                presentActivity(intent);
            }
        });
    }

//    @Override
//    protected boolean hideNavigationBar() {
//        return true;
//    }


    //    @Override
//    protected int getRightNaviMenuResourcesId() {
//        return R.menu.main;
//    }
//
//
//    @Override
//    protected void rightNaviItemsClick(MenuItem item) {
//        super.rightNaviItemsClick(item);
//        Log.e("bababba","bbbbbb");
//    }

    //    @Override
    protected int getMainLayoutId() {
       return R.layout.permissionactivity;
    }









}
