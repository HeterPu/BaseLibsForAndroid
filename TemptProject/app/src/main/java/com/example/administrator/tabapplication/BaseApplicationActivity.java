package com.example.administrator.tabapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.ViewGroup;

import com.heterpu.phbaselib.ui.module.permission.BasePermissionActivity;

import butterknife.ButterKnife;

public abstract class BaseApplicationActivity extends BasePermissionActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
        configuration();
    }


    protected void configuration(){

    }


}
