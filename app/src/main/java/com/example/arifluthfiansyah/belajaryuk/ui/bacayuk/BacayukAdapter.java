package com.example.arifluthfiansyah.belajaryuk.ui.bacayuk;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Berita;
import com.example.arifluthfiansyah.belajaryuk.network.model.Beritas;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arif Luthfiansyah on 26/09/2017.
 */

public class BacayukAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = BacayukAdapter.class.getSimpleName();
    private static BacayukListener mBacayukListener;
    private static List<Berita> mBeritas = new ArrayList<>();

    public BacayukAdapter(BacayukListener bacayukListener) {
        mBacayukListener = bacayukListener;
    }

    public void addBeritas(Beritas beritas) {
        mBeritas.addAll(beritas.getBeritas());
        notifyDataSetChanged();
    }

    public void clearBeritas() {
        mBeritas.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BacayukViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Context context = ((BacayukViewHolder) holder).itemView.getContext();
        String title = mBeritas.get(position).getJudul();
        String photo = mBeritas.get(position).getFoto();
        String content = mBeritas.get(position).getKonten();
        String source = "Sumber : " + mBeritas.get(position).getSumber();
        ((BacayukViewHolder) holder).mTitleTextView.setText(title);
        Glide.with(context)
                .load(photo)
                .centerCrop()
                .into(((BacayukViewHolder) holder).mPhotoImageView);
        ((BacayukViewHolder) holder).mContentTextView.setText(content);
        ((BacayukViewHolder) holder).mSourceTextView.setText(source);
    }

    @Override
    public int getItemCount() {
        return mBeritas.size();
    }

    public interface BacayukListener {
        void onBeritaItemClick(Berita berita);
    }

    static class BacayukViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        @BindView(R.id.content_item_berita)
        LinearLayout mBeritaLinearLayout;

        @BindView(R.id.iv_photo)
        ImageView mPhotoImageView;

        @BindView(R.id.tv_title)
        TextView mTitleTextView;

        @BindView(R.id.tv_content)
        TextView mContentTextView;

        @BindView(R.id.tv_source)
        TextView mSourceTextView;

        private BacayukViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        private static BacayukViewHolder create(ViewGroup parent) {
            return new BacayukViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_list_content_bacayuk, parent, false)
            );
        }

        @Override
        public void onClick(View v) {
            mBacayukListener.onBeritaItemClick(mBeritas.get(getAdapterPosition()));
        }
    }
}
