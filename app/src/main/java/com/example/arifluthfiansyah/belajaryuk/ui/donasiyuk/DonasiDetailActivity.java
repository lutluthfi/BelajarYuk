package com.example.arifluthfiansyah.belajaryuk.ui.donasiyuk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.arifluthfiansyah.belajaryuk.BaseActivity;
import com.example.arifluthfiansyah.belajaryuk.R;

import butterknife.OnClick;

/**
 * Created by Arif Luthfiansyah on 17/11/2017.
 */

public class DonasiDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasiyuk_detail);
    }

    @OnClick(R.id.btn_donasi)
    public void doDonasi(View view) {

    }
}
