package com.example.sashok.university.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sashok.university.R;
import com.example.sashok.university.helper.ColorUtil;

/**
 * Created by sashok on 4.10.17.
 */

public class Laba1Fragment extends AbsFragment {
    TextView hello_text;
    Button hello_btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View main_view=inflater.inflate(R.layout.lab_one_fragment,container,false);
        hello_text=(TextView)main_view.findViewById(R.id.text_hello_world);
        hello_btn=(Button) main_view.findViewById(R.id.hello_world_btn);
        hello_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hello_text.getVisibility()==View.INVISIBLE){
                    hello_text.setVisibility(View.VISIBLE);
                }
                hello_text.setTextColor(ColorUtil.getRandomMaterialColor(getContext(),"400"));
            }
        });
        return  main_view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static Laba1Fragment newInstance(Bundle args) {
        Laba1Fragment fragment = new Laba1Fragment();
        fragment.setArguments(args);
        return fragment;
    }
}
