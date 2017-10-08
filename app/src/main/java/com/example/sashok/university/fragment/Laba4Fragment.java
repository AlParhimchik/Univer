package com.example.sashok.university.fragment;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.sashok.university.R;
import com.example.sashok.university.adapter.EffectAdapter;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Created by sashok on 4.10.17.
 */

public class Laba4Fragment extends AbsFragment {
    DiscreteSeekBar seekBar;
    ImageView cat_one;
    ImageView cat_two;
    Button sync;
    private ListView mListView;
    private EffectAdapter mAdapter;
    private YoYo.YoYoString rope1;
    private YoYo.YoYoString rope2;
    public Boolean isSync = true;

    public static Laba4Fragment newInstance(Bundle args) {
        Laba4Fragment fragment = new Laba4Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View main_view = inflater.inflate(R.layout.lab_four_fragment, container, false);
        initialize(main_view);
        loadImages();
        setListeners();
        return main_view;
    }

    private void setListeners() {
        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSync = (isSync) ? false : true;
                if (isSync) sync.setText(R.string.synchronously);
                else sync.setText(R.string.asynchronously);
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (rope1!=null)
                    if (rope1.isRunning())
                        rope1.stop();
                if (rope2!=null)
                    if (rope2.isRunning())
                        rope2.stop();
                final Techniques technique = (Techniques) view.getTag();
                rope1=YoYo.with(technique)
                        .duration(seekBar.getProgress())
                        .pivot(YoYo.CENTER_PIVOT, YoYo.CENTER_PIVOT)
                        .interpolate(new AccelerateDecelerateInterpolator())
                        .withListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                if (isSync == false)
                                    rope2=YoYo.with(technique)
                                            .duration(seekBar.getProgress())
                                            .pivot(YoYo.CENTER_PIVOT, YoYo.CENTER_PIVOT)
                                            .interpolate(new AccelerateDecelerateInterpolator())
                                            .playOn(cat_two);
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {
                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .playOn(cat_one);
                if (isSync)
                    rope2=YoYo.with(technique)
                            .duration(seekBar.getProgress())
                            .pivot(YoYo.CENTER_PIVOT, YoYo.CENTER_PIVOT)
                            .interpolate(new AccelerateDecelerateInterpolator())
                            .playOn(cat_two);
            }
        });
    }

    private void loadImages() {
        Glide.with(getActivity()).load(R.drawable.kotik1).into(cat_one);
        Glide.with(getActivity()).load(R.drawable.kotik2).into(cat_two);
    }

    private void initialize(View main_view) {
        cat_one = (ImageView) main_view.findViewById(R.id.cat_one);
        cat_two = (ImageView) main_view.findViewById(R.id.cat_two);
        sync = (Button) main_view.findViewById(R.id.synchronously);
        mListView = (ListView) main_view.findViewById(R.id.list_items);
        mAdapter = new EffectAdapter(getActivity());
        mListView.setAdapter(mAdapter);
        seekBar = (DiscreteSeekBar) main_view.findViewById(R.id.seekbar);


    }


}
