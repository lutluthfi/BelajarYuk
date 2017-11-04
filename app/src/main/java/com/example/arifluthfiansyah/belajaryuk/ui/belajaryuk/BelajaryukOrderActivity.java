package com.example.arifluthfiansyah.belajaryuk.ui.belajaryuk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.arifluthfiansyah.belajaryuk.R;
import com.thunderrise.animations.PulseAnimation;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class BelajaryukOrderActivity extends AppCompatActivity implements
        BelajaryukCancelFragmentDialog.BelajaryukCancelListener {

    private static final String TAG = BelajaryukOrderActivity.class.getSimpleName();
    private final int durationPulse = 800;

    @BindView(R.id.iv_loading)
    CircleImageView mLoadingImageView;

    private BelajaryukCancelFragmentDialog mBelajaryukCancelFragment;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, BelajaryukOrderActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belajaryuk_order);
        ButterKnife.bind(this);
        onWaitingAcceptFromPengajar();
    }

    private void onWaitingAcceptFromPengajar() {
        PulseAnimation.create()
                .with(mLoadingImageView)
                .setDuration(durationPulse)
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

    private void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        showCancelFragmentDialog();
    }
}
