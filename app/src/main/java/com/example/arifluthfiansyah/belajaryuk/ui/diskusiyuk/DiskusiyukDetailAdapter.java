package com.example.arifluthfiansyah.belajaryuk.ui.diskusiyuk;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Jawaban;
import com.example.arifluthfiansyah.belajaryuk.network.model.Jawabans;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Arif Luthfiansyah on 12/11/2017.
 */

public class DiskusiyukDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = DiskusiyukDetailAdapter.class.getSimpleName();
    private static List<Jawaban> mJawabans = new ArrayList<>();
    private static DiskusiyukDetailListener mListener;

    public DiskusiyukDetailAdapter(DiskusiyukDetailListener diskusiyukDetailListener) {
        mListener = diskusiyukDetailListener;
    }

    public void addJawabans(Jawabans jawabans) {
        mJawabans.addAll(jawabans.getJawabans());
        notifyDataSetChanged();
    }

    public void clearJawabans() {
        mJawabans.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return DiskusiyukDetailViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Context context = ((DiskusiyukDetailViewHolder) holder).itemView.getContext();
        String photo = mJawabans.get(position).getFoto();
        String name = context.getResources().getString(R.string.example_name);
        String content = mJawabans.get(position).getKonten();

        Glide.with(context).load(photo).asBitmap().into(((DiskusiyukDetailViewHolder) holder).mPhotoUserImageView);
        ((DiskusiyukDetailViewHolder) holder).mNameUserTextView.setText(name);
        ((DiskusiyukDetailViewHolder) holder).mContentJawabanTextView.setText(content);
    }

    @Override
    public int getItemCount() {
        return mJawabans.size();
    }

    public interface DiskusiyukDetailListener {
        void openMenuItemJawaban();
    }

    static class DiskusiyukDetailViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        @BindView(R.id.iv_photo_user) CircleImageView mPhotoUserImageView;
        @BindView(R.id.tv_name_user) TextView mNameUserTextView;
        @BindView(R.id.tv_content_jawaban) TextView mContentJawabanTextView;
        @BindView(R.id.btn_more_jawaban) ImageButton mMoreJawabanButton;

        private DiskusiyukDetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mMoreJawabanButton.setOnClickListener(this);
        }

        private static DiskusiyukDetailViewHolder create(ViewGroup parent) {
            return new DiskusiyukDetailViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_list_content_diskusiyuk_detail, parent, false)
            );
        }

        @Override
        public void onClick(View view) {
            mListener.openMenuItemJawaban();
        }
    }
}
