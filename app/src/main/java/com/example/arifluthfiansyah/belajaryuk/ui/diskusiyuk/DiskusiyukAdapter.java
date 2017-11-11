package com.example.arifluthfiansyah.belajaryuk.ui.diskusiyuk;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pertanyaan;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pertanyaans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arif Luthfiansyah on 11/11/2017.
 */

public class DiskusiyukAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = DiskusiyukAdapter.class.getSimpleName();
    private static DiskusiyukListener mDiskusiyukListener;
    private static List<Pertanyaan> mPertanyaans = new ArrayList<>();

    public DiskusiyukAdapter(DiskusiyukListener diskusiyukListener) {
        mDiskusiyukListener = diskusiyukListener;
    }

    public void addPertanyaans(Pertanyaans pertanyaans) {
        mPertanyaans.addAll(pertanyaans.getPertanyaans());
        notifyDataSetChanged();
    }

    public void clearPertanyaans() {
        mPertanyaans.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mPertanyaans.size();
    }

    public interface DiskusiyukListener {
        void onPertanyaanItemClick(Pertanyaan pertanyaan);
    }

    //TODO terakhir sampai disini, buat list diskusiyuk
    static class DiskusiyukViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        public DiskusiyukViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        private static DiskusiyukViewHolder create(ViewGroup parent) {
            return new DiskusiyukViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_list_content_diskusiyuk, parent, false)
            );
        }

        @Override
        public void onClick(View view) {

        }
    }
}
