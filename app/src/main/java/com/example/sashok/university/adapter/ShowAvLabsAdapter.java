package com.example.sashok.university.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sashok.university.activity.MainActivity;
import com.example.sashok.university.R;
import com.example.sashok.university.helper.FragmentItem;

import java.util.List;

/**
 * Created by sashok on 4.10.17.
 */

public class ShowAvLabsAdapter extends RecyclerView.Adapter<ShowAvLabsAdapter.LabCardHolder> {
    private Activity activity;
    private List<FragmentItem> tags;

    public ShowAvLabsAdapter(Activity activity, List<FragmentItem> tags) {
        this.activity = activity;
        this.tags = tags;
    }

    @Override
    public LabCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.lab_card_item, parent, false);

        // Return a new holder instance
        LabCardHolder viewHolder = new LabCardHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LabCardHolder holder, final int position) {
        holder.lab_title.setText(tags.get(position).title);
        holder.lab_description.setText(tags.get(position).description);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) activity).onCardClicked(tags.get(position).tag);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    public class LabCardHolder extends RecyclerView.ViewHolder {
        private TextView lab_title;
        private  TextView lab_description;
        private CardView cardView;

        public LabCardHolder(View itemView) {
            super(itemView);
            lab_title = (TextView) itemView.findViewById(R.id.lab_name_title);
            lab_description=(TextView)itemView.findViewById(R.id.lab_name_description);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
}
