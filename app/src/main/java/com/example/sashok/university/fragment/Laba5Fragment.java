package com.example.sashok.university.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sashok.university.R;
import com.example.sashok.university.adapter.ImagesAdapter;
import com.example.sashok.university.helper.ImageUtil;
import com.example.sashok.university.interfaces.ImageInterface;

import java.util.List;

/**
 * Created by sashok on 4.10.17.
 */

public class Laba5Fragment extends AbsFragment implements ImageInterface {
    static final int GALLERY_REQUEST = 1;
    private ImageView imageView;
    private RecyclerView recyclerView;
    private ImagesAdapter mAdapter;
    private List<String>  images;
    public static Laba5Fragment newInstance(Bundle args) {
        Laba5Fragment fragment = new Laba5Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View main_view = inflater.inflate(R.layout.lab_five_fragment, container, false);
        imageView = (ImageView) main_view.findViewById(R.id.image_main);
        recyclerView = (RecyclerView) main_view.findViewById(R.id.image_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
        recyclerView.setHasFixedSize(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<String> list = ImageUtil.returnAllImages(getActivity());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onImagesRetrieved(list);
                    }
                });
            }
        }).start();

        return main_view;
    }

    public void onImagesRetrieved(List<String> images) {
        this.images = images;
        ImagesAdapter imagesAdapter = new ImagesAdapter(this, images);
        recyclerView.setAdapter(imagesAdapter);
    }

    @Override
    public void onImageClicked(String uri) {
        super.setNeedBack(false);
        recyclerView.setVisibility(View.INVISIBLE);
        Glide.with(getActivity()).load(uri).into(imageView);
        imageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (imageView.getVisibility()==View.VISIBLE){
            recyclerView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.INVISIBLE);
        }

        else super.onBackPressed();
    }
}
