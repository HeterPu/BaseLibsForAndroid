package com.heterpu.phbaselib.ui.activity;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.heterpu.phbaselib.R;
import com.heterpu.phbaselib.ui.module.permission.BasePermissionActivity;
import com.heterpu.phbaselib.ui.view.NoScrollViewPager;
import com.heterpu.phbaselib.utils.DisplayUtil;
import com.heterpu.phbaselib.utils.ViewFindUtils;

import java.util.ArrayList;

/**
 *
 */
public class BaseTabBarActivity extends BasePermissionActivity {




    /** ****   SETTING  DATA SOURCE ********
    *
    *
     * @return  Tab configuration entities
     * */
    protected ArrayList<TabEntity> getTabEntities(){
      return new ArrayList<>();
    }


    /**
     * @return Fragment array list
     */
    protected ArrayList<Fragment> getFragments(){
        return new ArrayList<>();
    }


    /**
     * @return Color of tab background
     */
    protected int getTabBackgroundColor(){
        return Color.RED;
    }


    /**
     * @return  Color of tab selected
     */
    protected int getTabSelectedColor(){
       return Color.CYAN;
    }


    /**
     * @return  Color of tab unselected
     */
    protected  int getTabUnselectedColor(){
        return Color.WHITE;
    }


    /**
     * @return get Tab bar height
     */
    protected int getTabBarHeight(){
        return 50;
    }


    /**
     * @return getTabIcon size
     */
    protected Point getTabIconSize(){
        return new Point(30,30);
    }


    /**
     * @return get tab bar outside inset
     */
    protected Rect getTabInset(){
        return new Rect(10,10,10,10);
    }


    /**
     * @return getIconTitle vertical padding
     */
    protected int getTabIconTitlePadding(){
        return 10;
    }


    /**
     * @return get title font size
     */
    protected int getTabTitleTextSize(){
        return 13;
    }


    /**
     * @return getTab initlization index
     */
    protected int getTabInitialIndex(){
        return 0;
    }





    /**
     *  ****   CONTROL EVENT  ****************
     *
     *
     * @return if pager switch smoothly,default is false.
     */
    protected boolean getPagerSmoothSwitch(){
        return false;
    }


    /**
     * @return Can pager scroll when user drag it
     */
    protected boolean getPagerScrollEnable(){
        return true;
    }





    /** ****   SETTING  DELEGATE  ********
     *
     *
     * @param position   the position tabBar item selected.
     * @param isReselected  the tabBar item is selected from switch or reTap.
     *
     * */
    protected void tabSelected(int position,boolean isReselected){

    }


    /**
     * @param pager the pager which selected
     * @param position  the position of pager selected.
     */
    protected  void pagerSelected(ViewPager pager,int position){

    }





    /**  Class Implementation */


    ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<TabEntity> mTabEntitiesImp = new ArrayList<>();
    private NoScrollViewPager mViewPager;
    private CommonTabLayout mTablayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        setContentView(R.layout.activity_basetabbar_layout);

        View mDecorView = getWindow().getDecorView();
        mViewPager = ViewFindUtils.find(mDecorView,R.id.vPager);
        mTablayout = ViewFindUtils.find(mDecorView,R.id.tablayout);

        //获取tab信息
        mTabEntitiesImp = getTabEntities();


        for (TabEntity entity:mTabEntitiesImp){
           TabEntityReal realEntity = new TabEntityReal(entity.getTabTitle(),entity.getTabSelectedIcon(),entity.getTabUnselectedIcon());
            mTabEntities.add(realEntity);
        }

        mFragments = getFragments();
        mViewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        mTablayout.setTabData(mTabEntities);
        initTabBarView();

        mTablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position,getPagerSmoothSwitch());
                tabSelected(position,false);
            }

            @Override
            public void onTabReselect(int position) {
                tabSelected(position,true);
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTablayout.setCurrentTab(position);
                pagerSelected(mViewPager,position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    protected boolean hideNavigationBar() {
        return true;
    }


    @Override
    protected int getMainLayoutId() {
        return R.layout.activity_basetabbar_layout;
    }

    private void initTabBarView(){
        mTablayout.setBackgroundColor(getTabBackgroundColor());
        mTablayout.setTextSelectColor(getTabSelectedColor());
        mTablayout.setTextUnselectColor(getTabUnselectedColor());
        ViewGroup.LayoutParams param = mTablayout.getLayoutParams();
        param.height = DisplayUtil.dip2px(this,getTabBarHeight());
        mTablayout.setLayoutParams(param);

        // icon 的资源id为0不显示icon
        if (mTabEntitiesImp.size() > 0){
            mTablayout.setIconVisible(mTabEntitiesImp.get(0).getTabSelectedIcon() != 0);
        }

        Point iconSize = getTabIconSize();
        mTablayout.setIconWidth(iconSize.x);
        mTablayout.setIconHeight(iconSize.y);

        mTablayout.setPadding(getTabInset().left,getTabInset().top,getTabInset().right,getTabInset().bottom);
        mTablayout.setIconMargin(getTabIconTitlePadding());

        mTablayout.setTextsize(getTabTitleTextSize());
        // 初始化及越界判断
        int initialIndex = getTabInitialIndex();
        if (initialIndex >= mTabEntitiesImp.size()){
            initialIndex = 0;
        }
        mTablayout.setCurrentTab(initialIndex);
        mViewPager.setCurrentItem(initialIndex,false);
        pagerSelected(mViewPager,initialIndex);
        mViewPager.setScrollEnable(getPagerScrollEnable());
    }


    // ADAPTER
    private class MainPagerAdapter extends FragmentPagerAdapter {
        private MainPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return  mTabEntities.get(position).getTabTitle();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }


    public static class TabEntity {
          String title;
          int selectedIcon;
          int unSelectedIcon;

        public TabEntity(String title,int selectedIcon,int unSelectedIcon){
            this.title = title;
            this.selectedIcon = selectedIcon;
            this.unSelectedIcon = unSelectedIcon;
        }

        public TabEntity() {
        }


        String getTabTitle() {
            return title;
        }

        int getTabSelectedIcon() {
            return selectedIcon;
        }

        int getTabUnselectedIcon() {
            return unSelectedIcon;
        }

    }


    public static class TabEntityReal implements CustomTabEntity {
        String title;
        int selectedIcon;
        int unSelectedIcon;

         TabEntityReal(String title,int selectedIcon,int unSelectedIcon){
            this.title = title;
            this.selectedIcon = selectedIcon;
            this.unSelectedIcon = unSelectedIcon;
        }

        public TabEntityReal() {
        }


        @Override
        public   String getTabTitle() {
            return title;
        }

        @Override
        public int getTabSelectedIcon() {
            return selectedIcon;
        }

        @Override
        public int getTabUnselectedIcon() {
            return unSelectedIcon;
        }

    }



}
