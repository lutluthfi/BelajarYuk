package com.example.arifluthfiansyah.belajaryuk.ui.pelajaran;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pelajaran;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pelajarans;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arif Luthfiansyah on 13/11/2017.
 */

public class PelajaranAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static List<Pelajaran> mPelajarans = new ArrayList<>();
    private static PelajaranAdapterListener mListener;

    public PelajaranAdapter(PelajaranAdapterListener pelajaranAdapterListener) {
        mListener = pelajaranAdapterListener;
    }

    public void addPelajarans(Pelajarans pelajarans) {
        mPelajarans.addAll(pelajarans.getPelajarans());
        notifyDataSetChanged();
    }

    public void clearPelajarans(){
        mPelajarans.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return DiskusiyukPelajaranViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Context context = ((DiskusiyukPelajaranViewHolder) holder).itemView.getContext();
        String pelajaran = mPelajarans.get(position).getNama();

        // Binding datas with views
        ((DiskusiyukPelajaranViewHolder) holder).mPelajaranTextView.setText(pelajaran);
    }

    @Override
    public int getItemCount() {
        return mPelajarans.size();
    }

    public interface PelajaranAdapterListener {
        void onPelajaranItemClick(Pelajaran pelajaran);
    }

    static class DiskusiyukPelajaranViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        @BindView(R.id.tv_pelajaran) TextView mPelajaranTextView;

        private DiskusiyukPelajaranViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        private static DiskusiyukPelajaranViewHolder create(ViewGroup parent) {
            return new DiskusiyukPelajaranViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_list_content_pelajaran, parent, false)
            );
        }

        @Override
        public void onClick(View view) {
            mListener.onPelajaranItemClick(mPelajarans.get(getAdapterPosition()));
        }
    }
}
