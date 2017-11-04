package com.example.arifluthfiansyah.belajaryuk.ui.bacayuk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Berita;
import com.thefinestartist.finestwebview.FinestWebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BacayukDetailActivity extends AppCompatActivity {

    private static final String TAG = BacayukDetailActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.sv_detail_bacayuk)
    NestedScrollView mDetailBacayukScrollView;

    @BindView(R.id.tv_title_detail_berita)
    TextView mTitleBeritaTextView;

    @BindView(R.id.iv_photo_detail_berita)
    ImageView mPhotoBeritaImageView;

    @BindView(R.id.tv_content_detail_berita)
    TextView mContentBeritaTextView;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, BacayukDetailActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bacayuk_detail);
        ButterKnife.bind(this);
        setupListener();
        setupToolbar();
        setupBeritaData();
    }

    private void setupListener() {
        // Blank
    }

    private Berita getBerita() {
        return (Berita) getIntent().getSerializableExtra("keyBerita");
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        String title = getBerita().getJudul();
        String created = getBerita().getCreatedAt();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setSubtitle(created);
        }
    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        if (toolbar != null) {
            int styleTitle = getResources().getIdentifier("Title.Toolbar", "style", getPackageName());
            int styleSubtitle = getResources().getIdentifier("Subtitle.Toolbar", "style", getPackageName());
            toolbar.setTitleTextAppearance(this, styleTitle);
            toolbar.setSubtitleTextAppearance(this, styleSubtitle);
            toolbar.setContentInsetStartWithNavigation(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_bacayuk, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_source:
                String source = getBerita().getSumber();
                new FinestWebView.Builder(this).show(source);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupBeritaData() {
        String titleBerita = getBerita().getJudul();
        String photoBerita = getBerita().getFoto();
        String content = getBerita().getKonten();
        mTitleBeritaTextView.setText(titleBerita);
        Glide.with(this)
                .load(photoBerita)
                .centerCrop()
                .into(mPhotoBeritaImageView);
        mContentBeritaTextView.setText(content);
    }
}
