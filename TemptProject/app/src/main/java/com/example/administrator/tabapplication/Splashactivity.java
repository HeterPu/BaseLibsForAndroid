package com.example.administrator.tabapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.tabapplication.BaseApplicationActivity;

public class Splashactivity extends BaseApplicationActivity {


    @Override
    protected boolean hideNavigationBar() {
       return true;
    }

    @Override
    protected boolean isFullScreenMode() {
        return true;
    }


    @Override
    protected void configuration() {
        super.configuration();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeBackHelper.forwardAndFinish(MainActivity.class);
            }
        }, 2000);
    }

    /**
     * 获取当前版本名称
     *
     * @return
     */
    public String getCurrentVersionName() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (Exception e) {
            // 利用系统api getPackageName()得到的包名，这个异常根本不可能发生
            return "";
        }
    }
}
