package com.example.arifluthfiansyah.belajaryuk.ui.diskusiyuk;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pertanyaan;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pertanyaans;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Arif Luthfiansyah on 11/11/2017.
 */

public class DiskusiyukAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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
        return DiskusiyukViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Context context = ((DiskusiyukViewHolder) holder).itemView.getContext();
        String course = mPertanyaans.get(position).getPelajaran();
        String title = mPertanyaans.get(position).getJudul();
        String photoUser = mPertanyaans.get(position).getUser().getFoto();
        String nameUser = mPertanyaans.get(position).getUser().getNama();
        String created = mPertanyaans.get(position).getCreatedAt();

        // Binding datas with views
        ((DiskusiyukViewHolder) holder).mCoursePertanyaanTextView.setText(course);
        ((DiskusiyukViewHolder) holder).mTitlePertanyaanTextView.setText(title);
        Glide.with(context).load(photoUser).asBitmap().into(((DiskusiyukViewHolder) holder).mPhotoUserImageView);
        ((DiskusiyukViewHolder) holder).mNameUserTextView.setText(nameUser);
        ((DiskusiyukViewHolder) holder).mCreatedPertanyaanTextView.setText(created);
    }

    @Override
    public int getItemCount() {
        return mPertanyaans.size();
    }

    public interface DiskusiyukListener {
        void onPertanyaanItemClick(Pertanyaan pertanyaan);
    }

    static class DiskusiyukViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        @BindView(R.id.tv_course_pertanyaan) TextView mCoursePertanyaanTextView;
        @BindView(R.id.tv_title_pertanyaan) TextView mTitlePertanyaanTextView;
        @BindView(R.id.iv_photo_user) CircleImageView mPhotoUserImageView;
        @BindView(R.id.tv_name_user) TextView mNameUserTextView;
        @BindView(R.id.tv_created_pertanyaan) TextView mCreatedPertanyaanTextView;

        private DiskusiyukViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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
            mDiskusiyukListener.onPertanyaanItemClick(mPertanyaans.get(getAdapterPosition()));
        }
    }
}
