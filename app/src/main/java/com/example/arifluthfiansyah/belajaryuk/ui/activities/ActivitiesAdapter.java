package com.example.arifluthfiansyah.belajaryuk.ui.activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arifluthfiansyah.belajaryuk.R;

/**
 * Created by Arif Luthfiansyah on 17/11/2017.
 */

public class ActivitiesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public ActivitiesAdapter() {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ActivitiesViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ActivitiesViewHolder extends RecyclerView.ViewHolder {
        private ActivitiesViewHolder(View itemView) {
            super(itemView);
        }

        private static ActivitiesViewHolder create(ViewGroup parent) {
            return new ActivitiesViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content_activities, parent, false));
        }
    }
}
