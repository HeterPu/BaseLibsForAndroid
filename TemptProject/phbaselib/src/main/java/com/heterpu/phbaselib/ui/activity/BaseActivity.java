package com.heterpu.phbaselib.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.heterpu.phbaselib.R;

public class BaseActivity extends AppCompatActivity {


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
