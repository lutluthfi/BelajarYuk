package com.example.arifluthfiansyah.belajaryuk.ui.belajaryuk;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pengajar;
import com.example.arifluthfiansyah.belajaryuk.network.model.Ulasan;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Arif Luthfiansyah on 27/10/2017.
 */

public class BelajaryukProfileFragmentDialog extends DialogFragment {

    private static final String TAG = BelajaryukProfileFragmentDialog.class.getSimpleName();
    private Context mContext;
    private Unbinder mUnbinder;

    @BindView(R.id.iv_photo_pengajar)
    CircleImageView mPhotoPengajarImageView;
    @BindView(R.id.tv_name_pengajar)
    TextView mNamePengajarTextView;
    @BindView(R.id.tv_degree_pengajar)
    TextView mDegreePengajarTextView;
    @BindView(R.id.tv_city_pengajar)
    TextView mCityPengajarTextView;
    @BindView(R.id.tv_bio_pengajar)
    TextView mBioPengajarTextView;
    @BindView(R.id.tv_rating_pengajar)
    TextView mRatingPengajarTextView;
    @BindView(R.id.tv_review_pengajar)
    TextView mReviewPengajarTextView;
    @BindView(R.id.tv_answers_pengajar)
    TextView mAnswersPengajarTextView;
    @BindView(R.id.tv_price_pengajar)
    TextView mPricePengajarTextView;

    public static BelajaryukProfileFragmentDialog newInstance(Pengajar pengajar) {
        BelajaryukProfileFragmentDialog fragmentDialog = new BelajaryukProfileFragmentDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("keyPengajar", pengajar);
        fragmentDialog.setArguments(bundle);
        return fragmentDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MyThemeFragmentDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_belajaryuk_profile, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setupPengajarData();
        return view;
    }

    private Pengajar getPengajarData() {
        if (getArguments() != null) {
            return (Pengajar) getArguments().getSerializable("keyPengajar");
        }
        return null;
    }

    private void setupPengajarData() {
        if (getPengajarData() != null) {
            String photoPengajar = getPengajarData().getFoto();
            String namePengajar = getPengajarData().getNama();
            String degreePengajar = getPengajarData().getPendidikanTerakhir();
            String cityPengajar = getPengajarData().getKota();
            String bioPengajar = getPengajarData().getBio();
            String ratingPengajar = getRatingPengajar(getPengajarData().getUlasans().getUlasans());
            int reviewPengajar = getPengajarData().getUlasans().getUlasans().size();
            int answersPengajar = getPengajarData().getJawabans().getJawabans().size();
            Glide.with(mContext)
                    .load(photoPengajar)
                    .asBitmap()
                    .centerCrop()
                    .into(mPhotoPengajarImageView);
            mNamePengajarTextView.setText(namePengajar);
            mDegreePengajarTextView.setText(degreePengajar);
            mCityPengajarTextView.setText(cityPengajar);
            mBioPengajarTextView.setText(bioPengajar);
            mRatingPengajarTextView.setText(ratingPengajar);
            mReviewPengajarTextView.setText(String.valueOf(reviewPengajar));
            mAnswersPengajarTextView.setText(String.valueOf(answersPengajar));
        }
    }

    private String getRatingPengajar(List<Ulasan> ulasans) {
        double ratingPengajar = 0;
        for (int i = 0; i < ulasans.size(); i++) {
            ratingPengajar += ulasans.get(i).getRating();
        }
        ratingPengajar = ratingPengajar / ulasans.size();
        return new DecimalFormat("##.#").format(ratingPengajar);
    }

    @OnClick(R.id.btn_order_pengajar)
    public void doOrderPengajar(View view) {
        Intent intent = BelajaryukOrderConfirmActivity.getStartIntent(mContext);
        intent.putExtra("keyPengajar", getPengajarData());
        startActivity(intent);
    }

    @OnClick(R.id.btn_call_pengajar)
    public void doCallPengajar(View view) {
        if (getPengajarData() != null) {
            String phonePengajar = getPengajarData().getNoTelp();
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phonePengajar));
            startActivity(intent);
        }
    }

    @OnClick(R.id.btn_profile_pengajar)
    public void openProfilePengajar(View view) {

    }

    @OnClick(R.id.btn_email_pengajar)
    public void doEmailPengajar(View view) {
        if (getPengajarData() != null) {
            String emailPengajar = getPengajarData().getEmail();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, emailPengajar);
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                showToastMessage(e.getMessage());
            }
        }
    }

    private void showToastMessage(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
