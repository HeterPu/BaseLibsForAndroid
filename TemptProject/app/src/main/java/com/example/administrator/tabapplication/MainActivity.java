package com.example.administrator.tabapplication;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.administrator.tabapplication.fragments.TestFragment1;
import com.example.administrator.tabapplication.fragments.TestFragment2;
import com.example.administrator.tabapplication.fragments.TestFragment3;
import com.example.administrator.tabapplication.fragments.TestFragment4;
import com.heterpu.phbaselib.ui.activity.BaseTabBarActivity;

import java.util.ArrayList;


public class MainActivity extends BaseTabBarActivity {


    private String[] mTitles = {"首页","消息","联系人","更多"};
    private int[] mIconUnselectedIds = {R.mipmap.addteam,R.mipmap.solution,R.mipmap.tabicon,R.mipmap.reconciliation};
    private int[] mIconSelectedIds = {R.mipmap.addteam1,R.mipmap.solution1,R.mipmap.tabicon1,R.mipmap.reconciliation1};

    private ArrayList<TabEntity> mTabEntities = new ArrayList<>();



    @Override
    protected ArrayList<TabEntity> getTabEntities() {
        ArrayList<TabEntity> entities = new ArrayList<>();
        for (int i = 0;i < mTitles.length;i++){
            TabEntity entity = new TabEntity(mTitles[i],mIconSelectedIds[i],mIconUnselectedIds[i]);
            entities.add(entity);
        }
        return entities;
    }


    @Override
    protected ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> mentites = new ArrayList<>();
        TestFragment1 fm1 = new TestFragment1();
        TestFragment2 fm2 = new TestFragment2();
        TestFragment3 fm3 = new TestFragment3();
        TestFragment4 fm4 = new TestFragment4();
        mentites.add(fm1);
        mentites.add(fm2);
        mentites.add(fm3);
        mentites.add(fm4);
        return mentites;
    }


    @Override
    protected int getTabBackgroundColor() {
        return Color.BLACK;
    }

    @Override
    protected int getTabBarHeight() {
        return 80;
    }


    @Override
    protected Point getTabIconSize() {
        return new Point(60,30);
    }



    @Override
    protected boolean getPagerScrollEnable() {
        return true;
    }

    @Override
    protected void pagerSelected(ViewPager pager, int position) {
        Log.e("POSITION",""+ position);
    }


}
