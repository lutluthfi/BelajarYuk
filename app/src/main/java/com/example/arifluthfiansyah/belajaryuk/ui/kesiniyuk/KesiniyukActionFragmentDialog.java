package com.example.arifluthfiansyah.belajaryuk.ui.kesiniyuk;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kegiatan;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Arif Luthfiansyah on 26/10/2017.
 */

public class KesiniyukActionFragmentDialog extends DialogFragment {

    private static final String TAG = KesiniyukActionFragmentDialog.class.getSimpleName();
    private Context mContext;
    private Unbinder mUnbinder;

    @BindView(R.id.tv_phone_penyelenggara)
    TextView mPhonePenyelenggaraTextView;

    @BindView(R.id.tv_email_penyelenggara)
    TextView mEmailPenyelenggaraTextView;

    public static KesiniyukActionFragmentDialog newInstance(Kegiatan kegiatan) {
        KesiniyukActionFragmentDialog fragmentDialog = new KesiniyukActionFragmentDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("keyKegiatan", kegiatan);
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
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_kesiniyuk_action, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setupKegiatanData();
        return view;
    }

    private Kegiatan getKegiatan() {
        return (Kegiatan) this.getArguments().getSerializable("keyKegiatan");
    }

    private void setupKegiatanData() {
        if (getKegiatan() != null) {
            String phonePenyelenggara = "Call " + getKegiatan().getPenyelenggara().getNoTelp();
            String emailPenyelenggara = "Email to " + getKegiatan().getPenyelenggara().getEmail();
            mPhonePenyelenggaraTextView.setText(phonePenyelenggara);
            mEmailPenyelenggaraTextView.setText(emailPenyelenggara);
        }
    }

    @OnClick(R.id.tv_phone_penyelenggara)
    public void doCallPenyelenggara(View view) {
        String phonePenyelenggara = getKegiatan().getPenyelenggara().getNoTelp();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phonePenyelenggara));
        startActivity(intent);
    }

    @OnClick(R.id.tv_email_penyelenggara)
    public void doEmailPenyelenggara(View view) {
        String emailPenyelenggara = getKegiatan().getPenyelenggara().getEmail();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, emailPenyelenggara);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            showToastMessage(e.getMessage());
        }
    }

    @OnClick(R.id.tv_report_kegiatan)
    public void doReportKegiatan(View view) {
        showToastMessage("Reported");
    }

    @OnClick(R.id.tv_copy_kegiatan)
    public void doCopyUrlKegiatan(View view) {
        showToastMessage("Copied!");
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
