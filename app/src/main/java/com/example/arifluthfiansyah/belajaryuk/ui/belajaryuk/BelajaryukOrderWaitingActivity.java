package com.example.arifluthfiansyah.belajaryuk.ui.belajaryuk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.arifluthfiansyah.belajaryuk.BaseActivity;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.thunderrise.animations.PulseAnimation;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class BelajaryukOrderWaitingActivity extends BaseActivity implements
        BelajaryukCancelFragmentDialog.BelajaryukCancelListener {

    @BindView(R.id.iv_loading) CircleImageView mLoadingImageView;

    private BelajaryukCancelFragmentDialog mBelajaryukCancelFragment;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, BelajaryukOrderWaitingActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belajaryuk_order_waiting);
        ButterKnife.bind(this);
        onWaitingAcceptFromPengajar();
    }

    private void onWaitingAcceptFromPengajar() {
        PulseAnimation.create()
                .with(mLoadingImageView)
                .setDuration(800)
                .setRepeatCount(PulseAnimation.INFINITE)
                .setRepeatMode(PulseAnimation.REVERSE)
                .start();
    }

    private void showCancelFragmentDialog() {
        mBelajaryukCancelFragment =
                BelajaryukCancelFragmentDialog.newInstance("Content");
        mBelajaryukCancelFragment.show(getSupportFragmentManager(), "Content");
    }

    @Override
    public void onYesCancelButtonClick() {
        String message = getResources().getString(R.string.prompt_yes_cancel);
        showToastMessage(message);
        finish();
    }

    @Override
    public void onNoCancelButtonClick() {
        String message = getResources().getString(R.string.prompt_no_cancel);
        showToastMessage(message);
        mBelajaryukCancelFragment.dismiss();
    }

    @Override
    public void onBackPressed() {
        showCancelFragmentDialog();
    }
}
