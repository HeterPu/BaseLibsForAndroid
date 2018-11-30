package com.example.administrator.tabapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.heterpu.phbaselib.ui.module.permission.BasePermissionActivity;

import butterknife.ButterKnife;

public class BaseApplicationActivity extends BasePermissionActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getMainLayoutId()!=0){
            int layoutId = getMainLayoutId();
            ViewGroup grop = (ViewGroup) this.getLayoutInflater().inflate(layoutId,null);
        }
        ButterKnife.bind(this);
        configuration();
    }

     protected  int getMainLayoutId(){
        return 0;
    }

    protected void configuration(){

    }


}
