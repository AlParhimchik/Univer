package com.example.sashok.university.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sashok.university.R;
import com.example.sashok.university.interfaces.ImageInterface;

import java.util.List;

/**
 * Created by sashok on 4.10.17.
 */

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImageHolder> {
    private Fragment context;
    private List<String> images;
    private ImageInterface listener;
    public ImagesAdapter(Fragment context, List<String> images) {
        this.context = context;
        this.images = images;
        listener=(ImageInterface)context;
    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.image_item, parent, false);

        // Return a new holder instance
        ImageHolder viewHolder = new ImageHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, final int position) {
        final String image = images.get(position);
        Glide.with(context).load(image).into(holder.main_image);
        holder.main_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onImageClicked(image);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder {
        private ImageView main_image;

        public ImageHolder(View itemView) {
            super(itemView);
            main_image = (ImageView) itemView.findViewById(R.id.main_image);

        }
    }
}
