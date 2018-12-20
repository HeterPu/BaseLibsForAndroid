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


    /**
     * Noted when view already created.
     */
    private boolean isViewCreated;

    /**
     * Noted when UI visible or not.
     */
    private boolean isUIVisible;

    /**
     * ContentView to hold display.
     */
    protected View mContentView;

    /**
     * Flag first enter.
     */
    private boolean isFirstEnter = true;


    public PagerFragment(){

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if ((mContentView != null)&&(!reloadMainLayoutAfterRecreate())){
            return mContentView;
        }
        View view = inflater.inflate(getMainLayoutId(), container, false);
        mContentView = view;
        onCreatingView(inflater,container,savedInstanceState,view);
        return view ;
    }


    /**
     *
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState saveInState
     * @param onCreatingView view which is creating.
     */
    protected void onCreatingView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState ,View onCreatingView) {

    }


    protected int getMainLayoutId(){
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



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //Reset flag
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

    /**
     * For fragment first load configuration.
     */
    protected void configuration(){
//        Log.i("ClassNameIs " +  getClass().getSimpleName(),"configuration");
    }


    /**
     * Fragment appear or disappear.
     * @param isVisible is fragment visible or not.
     */
    protected  void onFragmentVisibleChange(boolean isVisible){

    }


    /**
     * Default is false without reload main layout after recreate view.
     * @return Return true if reload.
     */
    protected  boolean reloadMainLayoutAfterRecreate(){
        return false;
    }


}
