package com.example.arifluthfiansyah.belajaryuk.ui.history;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arifluthfiansyah.belajaryuk.R;

/**
 * Created by Arif Luthfiansyah on 17/11/2017.
 */

public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public HistoryAdapter() {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return HistoryViewHolder.created(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        private HistoryViewHolder(View itemView) {
            super(itemView);
        }

        private static HistoryViewHolder created(ViewGroup parent) {
            return new HistoryViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content_history, parent, false));
        }
    }
}
