package com.heterpu.phbaselib.ui.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heterpu.phbaselib.R;


public class BaseActivity extends AppCompatActivity {

    protected Toolbar mNaviBar;
    protected TextView mTitleView;
    protected ConstraintLayout mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baseactivity_layout);
        mNaviBar = findViewById(R.id.baseActivity_Bar);
        mView =  findViewById(R.id.baseActivity_View);
        mTitleView = findViewById(R.id.baseActivity_Title);
        int visible = hideNavigationBar() ? View.GONE:View.VISIBLE;
        if (visible == View.VISIBLE){
            mNaviBar.setVisibility(visible);
            ConstraintLayout.LayoutParams paramContent = (ConstraintLayout.LayoutParams)mView.getLayoutParams();
            ConstraintLayout.LayoutParams paramNavi = (ConstraintLayout.LayoutParams)mNaviBar.getLayoutParams();
            paramContent.topMargin = paramNavi.height;
            mView.setLayoutParams(paramContent);
        }

        // set custom view
        if (getMainLayoutId()!=0){
            int layoutId = getMainLayoutId();
                ViewGroup contentV = (ViewGroup) this.getLayoutInflater().inflate(layoutId, null);
                ConstraintLayout.LayoutParams param = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                contentV.setLayoutParams(param);
                mView.addView(contentV);
        }


        if (!fullScreenMode() && getSupportActionBar() == null) {
            // setNaviBar
            mNaviBar.setTitle("");
            setSupportActionBar(mNaviBar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

            int naviIcon = getBackNaviItemResourceId();
            if (naviIcon != 0) {
                mNaviBar.setNavigationIcon(naviIcon);
                mNaviBar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        leftNaviItemClick();
                    }
                });
            }

        }

    }


    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (getIsTitleCenterInParent()){
            mTitleView.setText(title);
            mNaviBar.setTitle("");
        }else {
            mTitleView.setText("");
            mNaviBar.setTitle(title);
        }
    }



    protected void goBack(){
        if(!isTaskRoot())finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int menuResourceId = getRightNaviMenuResourcesId();
        if (menuResourceId != 0){
            getMenuInflater().inflate(menuResourceId,menu);
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        rightNaviItemsClick(item);
        return super.onOptionsItemSelected(item);
    }


    protected void rightNaviItemsClick(MenuItem item){

    }

    protected int getRightNaviMenuResourcesId(){
        return 0;
    }

    protected int getBackNaviItemResourceId(){
        return R.mipmap.whiteback;
    }


    protected void leftNaviItemClick(){

    }


    protected boolean getIsTitleCenterInParent(){
        return true;
    }

    protected  int getMainLayoutId(){
        return 0;
    }


    protected  boolean getIsIgnoreContentTopInset(){
        return true;
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


    protected boolean fullScreenMode(){
        return  true;

    }
}
