package com.example.administrator.tabapplication.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.tabapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TestFragment1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TestFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFragment1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TestFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static TestFragment1 newInstance(String param1, String param2) {
        TestFragment1 fragment = new TestFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentV = inflater.inflate(getLayoutId(), container, false);
        return contentV;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i("ClassNameIs " + getClass().getSimpleName(),"OnStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i( "ClassNameIs " + getClass().getSimpleName(),"OnResume");
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.i("ClassNameIs " +  getClass().getSimpleName(),"OnPause");
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.i( "ClassNameIs " + getClass().getSimpleName(),"OnStop");
    }


    protected int getLayoutId(){
        return  R.layout.fragment_test_fragment1;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            Log.i("ClassNameIs " +  getClass().getSimpleName(),"Is  Visible ");
        }
        }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
