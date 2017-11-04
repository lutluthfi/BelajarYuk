package com.example.arifluthfiansyah.belajaryuk.ui.donasiyuk;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kampanye;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kampanyes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Arif Luthfiansyah on 24/10/2017.
 */

public class DonasiyukAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = DonasiyukAdapter.class.getSimpleName();
    private static DonasiyukListener mDonasiyukListener;
    private static List<Kampanye> mKampanyes = new ArrayList<>();

    public DonasiyukAdapter(DonasiyukListener donasiyukListener) {
        mDonasiyukListener = donasiyukListener;
    }

    public void addKampanyes(Kampanyes kampanyes) {
        mKampanyes.addAll(kampanyes.getKampanye());
        notifyDataSetChanged();
    }

    public void clearKampanye() {
        mKampanyes.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return DonasiyukViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Context context = ((DonasiyukViewHolder) holder).itemView.getContext();
        String namePenggalang = mKampanyes.get(position).getPenggalang();
        String addressKampanye = mKampanyes.get(position).getAlamat();
        String titleKampanye = mKampanyes.get(position).getJudul();
        String photoKampanye = mKampanyes.get(position).getFoto();
        ((DonasiyukViewHolder) holder).mNamePenggalangTextView.setText(namePenggalang);
        ((DonasiyukViewHolder) holder).mAddressKampanyeTextView.setText(addressKampanye);
        ((DonasiyukViewHolder) holder).mTitleKampanyeTextView.setText(titleKampanye);
        Glide.with(context)
                .load(photoKampanye)
                .asBitmap()
                .centerCrop()
                .into(((DonasiyukViewHolder) holder).mPhotoKampanyeImageView);
    }

    @Override
    public int getItemCount() {
        return mKampanyes.size();
    }

    public interface DonasiyukListener {
        void onKampanyeItemClick(Kampanye kampanye);
    }

    static class DonasiyukViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        @BindView(R.id.iv_photo_penggalang)
        CircleImageView mPhotoPenggalangImageView;

        @BindView(R.id.tv_name_penggalang)
        TextView mNamePenggalangTextView;

        @BindView(R.id.btn_more_kampanye)
        ImageButton mMoreKampanyeButton;

        @BindView(R.id.iv_photo_kampanye)
        ImageView mPhotoKampanyeImageView;

        @BindView(R.id.tv_address_kampanye)
        TextView mAddressKampanyeTextView;

        @BindView(R.id.tv_title_kampanye)
        TextView mTitleKampanyeTextView;

        private DonasiyukViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        private static DonasiyukViewHolder create(ViewGroup parent) {
            return new DonasiyukViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_list_content_donasiyuk, parent, false)
            );
        }

        @Override
        public void onClick(View v) {
            mDonasiyukListener.onKampanyeItemClick(mKampanyes.get(getAdapterPosition()));
        }
    }
}
