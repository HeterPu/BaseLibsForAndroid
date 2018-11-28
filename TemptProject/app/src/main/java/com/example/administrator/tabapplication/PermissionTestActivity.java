package com.example.administrator.tabapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.heterpu.phbaselib.ui.module.permission.BasePermissionActivity;
import com.heterpu.phbaselib.ui.module.permission.MainActivityPermissionDispatcher;

public class PermissionTestActivity extends BasePermissionActivity {




    private  Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permissionactivity);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             checkDevicePermissionWith(MainActivityPermissionDispatcher.REQUEST_CAMERA, new PermissionCallBack() {
                 @Override
                 public boolean allowed(int requestCode) {
                     return false;
                 }

                 @Override
                 public boolean denied(int requestCode) {
                     return false;
                 }

                 @Override
                 public boolean rationale(PermissRequestion request, int requestCode) {
                     return false;
                 }

                 @Override
                 public boolean neverask(int requestCode) {
                     return false;
                 }
             });
            }
        });
    }

}
