package com.example.sashok.university.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.sashok.university.R;
import com.example.sashok.university.activity.BrowserActivity;

/**
 * Created by sashok on 4.10.17.
 */

public class Laba8Fragment extends AbsFragment {
    WebView webView;
    ProgressBar progressBar;
    private float m_downX;

    public static Laba8Fragment newInstance(Bundle args) {
        Laba8Fragment fragment = new Laba8Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View main = inflater.inflate(R.layout.lab_eight_fragment, container, false);
        Button btn=(Button)main.findViewById(R.id.go_btn);
        final EditText editText=(EditText)main.findViewById(R.id.page_adress_edit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), BrowserActivity.class);
                intent.putExtra("url",editText.getText().toString());
                startActivity(intent);
            }
        });
        return  main;
    }
}

