package com.example.arifluthfiansyah.belajaryuk.ui.belajaryuk;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pengajar;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pengajars;
import com.example.arifluthfiansyah.belajaryuk.network.model.Ulasan;
import com.example.arifluthfiansyah.belajaryuk.network.model.Ulasans;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arif Luthfiansyah on 26/10/2017.
 */

public class BelajaryukAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = BelajaryukAdapter.class.getSimpleName();

    private static BelajaryukListener mBelajaryukListener;
    private static List<Pengajar> mPengajars = new ArrayList<>();

    public BelajaryukAdapter(BelajaryukListener belajaryukListener) {
        mBelajaryukListener = belajaryukListener;
    }

    public void addPengajars(Pengajars pengajars) {
        mPengajars.addAll(pengajars.getPengajars());
        notifyDataSetChanged();
    }

    public void clearPengajars() {
        mPengajars.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BelajaryukViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Context context = ((BelajaryukViewHolder) holder).itemView.getContext();
        String photoPengajar = mPengajars.get(position).getFoto();
        String namePengajar = mPengajars.get(position).getNama();
        int availablePengajar = mPengajars.get(position).getAktif();
        String ratingPengajar = getRatingPengajar(mPengajars.get(position).getUlasans().getUlasans());
        setupAvailablePengajar(
                ((BelajaryukViewHolder) holder).mAvailablePengajarImageView,
                availablePengajar
        );
        Glide.with(context)
                .load(photoPengajar)
                .asBitmap()
                .centerCrop()
                .into(((BelajaryukViewHolder) holder).mPhotoPengajarImageView);
        ((BelajaryukViewHolder) holder).mNamePengajarTextView.setText(namePengajar);
        ((BelajaryukViewHolder) holder).mRatingPengajarTextView.setText(ratingPengajar);
    }

    private void setupAvailablePengajar(ImageView iv, int available) {
        if (available == 1) {
            iv.setVisibility(View.INVISIBLE);
        } else {
            iv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mPengajars.size();
    }

    public interface BelajaryukListener {
        void onPengajarItemClick(Pengajar pengajar);
    }

    private String getRatingPengajar(List<Ulasan> ulasans) {
        double ratingPengajar = 0;
        for (int i = 0; i < ulasans.size(); i++) {
            ratingPengajar += ulasans.get(i).getRating();
        }
        ratingPengajar = ratingPengajar / ulasans.size();
        return new DecimalFormat("##.#").format(ratingPengajar);
    }

    static class BelajaryukViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        @BindView(R.id.iv_available_pengajar)
        ImageView mAvailablePengajarImageView;

        @BindView(R.id.iv_photo_pengajar)
        ImageView mPhotoPengajarImageView;

        @BindView(R.id.tv_name_pengajar)
        TextView mNamePengajarTextView;

        @BindView(R.id.tv_rating_pengajar)
        TextView mRatingPengajarTextView;

        private BelajaryukViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        private static BelajaryukViewHolder create(ViewGroup parent) {
            return new BelajaryukViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_list_content_belajaryuk, parent, false)
            );
        }

        @Override
        public void onClick(View v) {
            mBelajaryukListener.onPengajarItemClick(mPengajars.get(getAdapterPosition()));
        }
    }
}
