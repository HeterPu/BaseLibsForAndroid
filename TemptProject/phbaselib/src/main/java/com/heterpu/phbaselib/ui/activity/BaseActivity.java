package com.heterpu.phbaselib.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

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


        if (getSupportActionBar() == null) {
            // setNaviBar
            mNaviBar.setTitle("");
            setSupportActionBar(mNaviBar);
//            if (getSupportActionBar() != null) {
//                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            }

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


    public void setTitle_Color(int color) {
        if (getIsTitleCenterInParent()){
            mTitleView.setTextColor(color);
        }else {
            mNaviBar.setTitleTextColor(color);
        }
    }




    protected void goBack(){
       goBack(true);
    }


    protected void goBack(boolean animated){
        // 判断是否是根activity,根activity不返回
        if(!isTaskRoot())
        {
            if (isPresentedActivity()){
                dismissActivity(animated);
            }else {
                popActivity(animated);
            }
        }
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
        goBack();
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

    protected boolean isPresentedActivity() {
        return false;
    }



    /*   ACTIVITY JUMP START  */


    /**
     *  Push activity like ios from left to
     *  right
     */
    public void pushActivity(Intent intent){
        pushActivity(intent,true);
    }


    public void pushActivity(Intent intent,boolean animated){
        beforeJumpPrepareWork(true);
        startActivity(intent);
        if (customJumpAnimation(true))return;
        startPushAnimation(animated);
    }

    public void pushActivityForResult(Intent intent,int requestCode,boolean animated){
        startActivityForResult(intent,requestCode);
        if (customJumpAnimation(true))return;
        startPushAnimation(animated);
    }

    /**
     *  Present activity like ios ，from bottom to top
     */
    public void presentActivity(Intent intent){
        presentActivity(intent,true);
    }


    public void presentActivity(Intent intent,boolean animated){
        beforeJumpPrepareWork(true);
        startActivity(intent);
        if (customJumpAnimation(true))return;
        startPresentAnimation(animated);
    }

    public void presentActivityResult(Intent intent,int requestCode,boolean animated){
        startActivityForResult(intent,requestCode);
        if (customJumpAnimation(true))return;
        startPresentAnimation(animated);
    }


    /**
     *  Pop activity like ios from right to
     *  left
     */
    public void popActivity(){
        popActivity(true);
    }

    public void popActivity(boolean animated){
        beforeJumpPrepareWork(false);
        finish();
        if (customJumpAnimation(false))return;
        startPopAnimation(animated);
    }


    /**
     *  Pop activity like ios from top to
     *  bottom
     */
    public void dismissActivity(){
        dismissActivity(true);
    }

    public void dismissActivity(boolean animated){
        beforeJumpPrepareWork(false);
        finish();
        if (customJumpAnimation(false))return;
        startDismissAnimation(animated);
    }


    /**
     *  Do some clean and prepareWork for jump.
     */
    protected void beforeJumpPrepareWork(boolean isJumpForeward){

    }


    /**
     *  DEFAULT RETURN VALUE IS FALSE, YOU CAN CUSTOM THIS VALUE FROM OVERRIDING overridePendingTransition(),
     *  AND RETURN TRUE TO CUSTOM JUMP ANIMATION.
     */
    protected boolean customJumpAnimation(boolean isJumpForeward){
        return false;
    }


    /*   ACTIVITY JUMP END  */

    /*   ANIMATION START  */

    private void startPushAnimation(boolean animated){
        if (!animated) {
            overridePendingTransition(0, 0);
        }else {
            overridePendingTransition(R.anim.bga_activity_forward_enter, R.anim.bga_activity_forward_exit);
        }
    }


    private void startPresentAnimation(boolean animated){
        if (!animated) {
            overridePendingTransition(0, 0);
        }else {
            overridePendingTransition(R.anim.activity_bottom_top_in,R.anim.activity_bottom_top_out);
        }
    }


    private void startPopAnimation(boolean animated){
        if (!animated) {
            overridePendingTransition(0, 0);
        }else {
            overridePendingTransition(R.anim.bga_activity_backward_enter, R.anim.bga_activity_backward_exit);
        }
    }


    private void startDismissAnimation(boolean animated){
        if (!animated) {
            overridePendingTransition(0, 0);
        }else {
            overridePendingTransition(0,R.anim.activity_top_bottom_out);
        }
    }


    /*   ANIMATION END  */


}
