package com.example.sashok.university.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sashok.university.R;

/**
 * Created by sashok on 4.10.17.
 */

public class Laba2Fragment extends AbsFragment {
    public static Laba2Fragment newInstance(Bundle args) {

        Laba2Fragment fragment = new Laba2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View main_view=inflater.inflate(R.layout.lab_two_fragment,container,false);
        return  main_view;
    }
}
