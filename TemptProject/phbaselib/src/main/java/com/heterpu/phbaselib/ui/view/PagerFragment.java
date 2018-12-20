package com.heterpu.phbaselib.ui.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PagerFragment extends Fragment {


    //Fragment的View加载完毕的标记
    private boolean isViewCreated;
    //Fragment对用户可见的标记
    private boolean isUIVisible;
    protected View rootView;
    private boolean isFirstEnter = true;



    public PagerFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater  inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView != null){
            return rootView;
        }
        View view = inflater.inflate(getLayoutId(), container, false);
        rootView = view;
        return view ;
    }


    protected int getLayoutId(){
        return 0;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        if (getUserVisibleHint()) {
//            Log.i("ClassNameIs " +  getClass().getSimpleName(),"viewcreate ");
            if (isFirstEnter)configuration();
            isFirstEnter = false;
            onFragmentVisibleChange(isVisible());
        }
    }


    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            loadData();
            //数据加载完毕,恢复标记,防止重复加载
        }
    }


    protected  void loadData(){

    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //页面销毁,恢复标记
        isViewCreated = false;
        isUIVisible = false;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isViewCreated)return;
        isUIVisible = isVisibleToUser;
//        Log.i("ClassNameIs " +  getClass().getSimpleName(),"uservisible ");
        if (isFirstEnter)configuration();
        isFirstEnter = false;
        onFragmentVisibleChange(isUIVisible);
    }


    protected void configuration(){
        Log.i("ClassNameIs " +  getClass().getSimpleName(),"configuration");
    }

    protected  void onFragmentVisibleChange(boolean isVisible){

    }

}
