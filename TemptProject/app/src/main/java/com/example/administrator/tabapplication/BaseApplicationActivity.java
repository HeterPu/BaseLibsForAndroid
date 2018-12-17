package com.example.administrator.tabapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;


import com.heterpu.phbaselib.ui.module.permission.BasePermissionActivity;
import com.jaeger.library.StatusBarUtil;

import butterknife.ButterKnife;
import cn.bingoogolapple.swipebacklayout.BGAKeyboardUtil;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

public abstract class BaseApplicationActivity extends BasePermissionActivity implements BGASwipeBackHelper.Delegate{


    protected BGASwipeBackHelper mSwipeBackHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 在 super.onCreate(savedInstanceState) 之前调用该方法
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        configuration();
    }



    /* SET PAGE SLIDE  START */

    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);
        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。

        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
        // 设置底部导航条是否悬浮在内容上，默认值为 false
        mSwipeBackHelper.setIsNavigationBarOverlap(false);
    }


    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     *
     * @return
     */
    @Override
    public boolean isSupportSwipeBack() {
       // 当被presented出来时默认不返回
       return !isPresentedActivity();
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset 从 0 到 1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {
    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {
    }

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

    @Override
    public void onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper.isSliding()) {
            return;
        }
        goBack();
    }


    /* SET PAGE SLIDE END */



    /* SET STATUS BAR START */
    /**
     * 设置状态栏颜色
     *
     * @param color
     */
    protected void setStatusBarColor(@ColorInt int color) {


        setStatusBarColor(color, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 设置状态栏颜色
     *
     * @param color
     * @param statusBarAlpha 透明度
     */
    public void setStatusBarColor(@ColorInt int color, @IntRange(from = 0, to = 255) int statusBarAlpha) {
        if (isFullScreenMode()) {
            StatusBarUtil.setTranslucentForImageViewInFragment(this,0, null);
        }else {
            StatusBarUtil.setColorForSwipeBack(this, color, statusBarAlpha);
        }
    }



    /**
     * 设置状态栏颜色
     *
     */
    protected void refreshNaviBarAndStatusBarState(){
        int statusBarAlpha = getStatusBarAlpha();
        int color = getNaviBarBackgroundColor();
        mNaviBar.setBackgroundColor(color);
        setStatusBarColor(color,statusBarAlpha);
    }


    @Override
    protected void onStart() {
        super.onStart();
        // setMode
        if (preferStatusBarStyle()){
            StatusBarUtil.setLightMode(this);
        }else {
            StatusBarUtil.setDarkMode(this);
        }
    }

    protected @IntRange(from = 0, to = 255) int getStatusBarAlpha(){
//        return  StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA;
        return 0;
    }


    /**
     * 设置状态栏颜色
     *
     *  default is LightMode
     */
    protected  boolean preferStatusBarStyle(){
        return  false;
    }


    /**
     * 设置导航栏颜色
     *
     *  default is 系统的主题色
     */
    protected int getNaviBarBackgroundColor(){
        return Color.RED;
//        return getResources().getColor(R.color.colorPrimary);
    }


    /**
     * 设置沉浸式状态栏开关
     *
     *  default is 系统的主题色
     */
    protected boolean isFullScreenMode(){
        return false;
    }


    /* SET STATUS BAR END */

    protected void configuration(){
         refreshNaviBarAndStatusBarState();
    }


//    @Override
//    protected void leftNaviItemClick() {
////        super.leftNaviItemClick();
//       goBack(false);
//    }



    @Override
    protected void beforeJumpPrepareWork(boolean isJumpForeward) {
        super.beforeJumpPrepareWork(isJumpForeward);
        BGAKeyboardUtil.closeKeyboard(this);
    }
}
