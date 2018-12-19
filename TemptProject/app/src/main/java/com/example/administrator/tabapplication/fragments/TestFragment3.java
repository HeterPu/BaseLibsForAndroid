package com.example.administrator.tabapplication.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.tabapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TestFragment3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TestFragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFragment3 extends TestFragment1 {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_fragment3;
    }
}
