package com.example.arifluthfiansyah.belajaryuk.ui.belajaryuk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.arifluthfiansyah.belajaryuk.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BelajaryukOrderConfirmActivity extends AppCompatActivity {

    private static final String TAG = BelajaryukOrderConfirmActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    //TODO Kelarin bisnis prosesnya jangan lupa!!!!

    public static Intent getStartIntent(Context context) {
        return new Intent(context, BelajaryukOrderConfirmActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belajaryuk_order_confirm);
        ButterKnife.bind(this);
        setupToolbar();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        String title = getResources().getString(R.string.title_feature_belajaryuk_order);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setContentInsetStartWithNavigation(0);
        }
    }

    @OnClick(R.id.btn_order_pengajar)
    public void doOrderPengajar(View view) {
        Intent intent = BelajaryukOrderActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }
}
