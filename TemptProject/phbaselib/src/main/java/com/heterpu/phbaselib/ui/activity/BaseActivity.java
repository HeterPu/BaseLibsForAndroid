package com.heterpu.phbaselib.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.heterpu.phbaselib.R;


public class BaseActivity extends AppCompatActivity {

    protected Toolbar mNaviBar;
    protected ConstraintLayout mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baseactivity_layout);
        mNaviBar = findViewById(R.id.baseActivity_Bar);
        mView =  findViewById(R.id.baseActivity_View);
    }


    protected boolean hideNavigationBar(){
        return false;
    }



    // Convenient tool method


    @Override
    public void finish() {
        super.finish();
        if (isPresentedActivity())overridePendingTransition(0,R.anim.activity_top_bottom_out);
    }


    protected boolean isPresentedActivity() {
        return false;
    }

    public void pushActivity(Intent intent){
        startActivity(intent);
    }


    public void presentActivity(Intent intent){
        startActivity(intent);
        overridePendingTransition(R.anim.activity_bottom_top_in,R.anim.activity_bottom_top_out);
    }

    public void pushActivityForResult(Intent intent,int requestCode){
        startActivityForResult(intent,requestCode);
        startActivity(intent);
    }


    public void presentActivityResult(Intent intent,int requestCode){
        startActivityForResult(intent,requestCode);
        overridePendingTransition(R.anim.activity_bottom_top_in,R.anim.activity_bottom_top_out);
    }
}
