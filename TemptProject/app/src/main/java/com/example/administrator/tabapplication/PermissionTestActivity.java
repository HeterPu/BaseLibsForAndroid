package com.example.administrator.tabapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.heterpu.phbaselib.ui.module.permission.BasePermissionActivity;
import com.heterpu.phbaselib.ui.module.permission.MainActivityPermissionDispatcher;

public class PermissionTestActivity extends BasePermissionActivity {

    private  Button mButton;
    private  Button mButton2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permissionactivity);
        mButton = findViewById(R.id.button);
        mButton2 = findViewById(R.id.button2);
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


    private void testPermission(){

    }






}
