package com.example.sashok.university.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.sashok.university.R;
import com.example.sashok.university.activity.MainActivity;
import com.example.sashok.university.helper.ColorUtil;

/**
 * Created by sashok on 4.10.17.
 */

public class Laba3Fragment extends AbsFragment {

    Button change_color;
    Button information;
    RelativeLayout layout;


    public static Laba3Fragment newInstance(Bundle args) {

        Laba3Fragment fragment = new Laba3Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View main_view = inflater.inflate(R.layout.lab_three_fragment, container, false);
        change_color=(Button)main_view.findViewById(R.id.change_color_btn);
        information=(Button) main_view.findViewById(R.id.info_btn);
        layout=(RelativeLayout)main_view.findViewById(R.id.lab_three_layout);
        change_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setBackgroundColor(ColorUtil.getRandomMaterialColor(getContext(),"500"));
            }
        });

        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Информация о разработчике")
                        .setMessage("Пархимчик Александр Витальевич\nгруппа 520601")
                        .setCancelable(false)
                        .setNegativeButton("поставить 10",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        return main_view;
    }
}
