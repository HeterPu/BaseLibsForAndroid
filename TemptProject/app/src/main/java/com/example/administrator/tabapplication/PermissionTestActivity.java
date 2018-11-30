package com.example.administrator.tabapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.widget.Button;

import com.heterpu.phbaselib.ui.module.permission.BasePermissionActivity;


public class PermissionTestActivity extends BasePermissionActivity {

    private  Button mButton;
    private  Button mButton2;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView.setBackgroundColor(Color.CYAN);



//        mButton = findViewById(R.id.button);
//        mButton2 = findViewById(R.id.button2);
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//             checkDevicePermissionWith(MainActivityPermissionDispatcher.REQUEST_READWRITECONTACTS, new PermissionCallBack() {
//                 @Override
//                 public boolean allowed(int requestCode) {
//                     return false;
//                 }
//
//                 @Override
//                 public boolean denied(int requestCode) {
//                     return false;
//                 }
//
//                 @Override
//                 public boolean rationale(PermissionRequestion request, int requestCode) {
//                     return false;
//                 }
//
//                 @Override
//                 public boolean neverask(int requestCode) {
//                     return false;
//                 }
//             });
//            }
//        });
//
//        mButton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(PermissionTestActivity.this,TestJumpAndPresentedActivity.class);
//               presentActivity(intent);
//            }
//        });
    }




    private void testPermission(){

    }






}
