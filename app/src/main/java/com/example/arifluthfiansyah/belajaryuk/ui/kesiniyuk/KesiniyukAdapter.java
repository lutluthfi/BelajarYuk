package com.example.arifluthfiansyah.belajaryuk.ui.kesiniyuk;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kegiatan;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kegiatans;
import com.example.arifluthfiansyah.belajaryuk.ui.bacayuk.BacayukAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Arif Luthfiansyah on 21/10/2017.
 */

public class KesiniyukAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = KesiniyukAdapter.class.getSimpleName();
    private static KesiniyukListener mKesiniyukListener;
    private static List<Kegiatan> mKegiatans = new ArrayList<>();

    public KesiniyukAdapter(KesiniyukListener kesiniyukListener) {
        mKesiniyukListener = kesiniyukListener;
    }

    public void addKegiatans(Kegiatans kegiatans) {
        mKegiatans.addAll(kegiatans.getKegiatans());
        notifyDataSetChanged();
    }

    public void clearKegiatans() {
        mKegiatans.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return KesiniyukViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Context context = ((KesiniyukViewHolder) holder).itemView.getContext();
        String titleKegiatan = mKegiatans.get(position).getJudul();
        String photoKegiatan = mKegiatans.get(position).getFoto();
        String timeKegiatan = mKegiatans.get(position).getWaktu();
        String addressKegiatan = mKegiatans.get(position).getLokasi();
        String namePenyelenggara = mKegiatans.get(position).getPenyelenggara().getNama();
        String phonePenyelenggara = mKegiatans.get(position).getPenyelenggara().getNoTelp();
        String emailPenyelenggara = mKegiatans.get(position).getPenyelenggara().getEmail();

        ((KesiniyukViewHolder) holder).mTitleKegiatanTextView.setText(titleKegiatan);
        Glide.with(context)
                .load(photoKegiatan)
                .asBitmap()
                .centerCrop()
                .into(((KesiniyukViewHolder) holder).mPhotoKegiatanImageView);
        ((KesiniyukViewHolder) holder).mTimeKegiatanTextView.setText(timeKegiatan);
        ((KesiniyukViewHolder) holder).mAddressKegiatanTextView.setText(addressKegiatan);
        ((KesiniyukViewHolder) holder).mNamePenyelenggaraTextView.setText(namePenyelenggara);
    }

    @Override
    public int getItemCount() {
        return mKegiatans.size();
    }

    public interface KesiniyukListener {
        void openDetailKesiniyuk(Kegiatan kegiatan);

        void openMenuItemKegiatan(Kegiatan kegiatan);
    }

    static class KesiniyukViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        @BindView(R.id.iv_photo_penyelenggara)
        CircleImageView mPhotoPenyelenggaraImageView;

        @BindView(R.id.tv_name_penyelenggara)
        TextView mNamePenyelenggaraTextView;

        @BindView(R.id.btn_more_kegiatan)
        ImageButton mMoreMenuKegiatanImageButton;

        @BindView(R.id.tv_title_kegiatan)
        TextView mTitleKegiatanTextView;

        @BindView(R.id.iv_photo_kegiatan)
        ImageView mPhotoKegiatanImageView;

        @BindView(R.id.tv_time_kegiatan)
        TextView mTimeKegiatanTextView;

        @BindView(R.id.tv_address_kegiatan)
        TextView mAddressKegiatanTextView;

        private KesiniyukViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            setupListener();
        }

        private static KesiniyukViewHolder create(ViewGroup parent) {
            return new KesiniyukViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_list_content_kesiniyuk, parent, false)
            );
        }

        private void setupListener() {
            mTitleKegiatanTextView.setOnClickListener(this);
            mPhotoKegiatanImageView.setOnClickListener(this);
            mTimeKegiatanTextView.setOnClickListener(this);
            mAddressKegiatanTextView.setOnClickListener(this);
            mMoreMenuKegiatanImageButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.btn_more_kegiatan) {
                mKesiniyukListener.openMenuItemKegiatan(mKegiatans.get(getAdapterPosition()));
            } else {
                mKesiniyukListener.openDetailKesiniyuk(mKegiatans.get(getAdapterPosition()));
            }
        }
    }
}
