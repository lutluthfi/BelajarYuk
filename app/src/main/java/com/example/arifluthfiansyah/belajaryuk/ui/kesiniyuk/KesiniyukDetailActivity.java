package com.example.arifluthfiansyah.belajaryuk.ui.kesiniyuk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kegiatan;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class KesiniyukDetailActivity extends AppCompatActivity {

    private static final String TAG = KesiniyukDetailActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.sv_detail_kesiniyuk)
    NestedScrollView mDetailKesiniyukScrollView;

    @BindView(R.id.tv_title_detail_kegiatan)
    TextView mTitleKegiatanTextView;

    @BindView(R.id.iv_photo_detail_kegiatan)
    ImageView mPhotoKegiatanImageView;

    @BindView(R.id.tv_time_detail_kegiatan)
    TextView mTimeKegiatanTextView;

    @BindView(R.id.tv_location_detail_kegiatan)
    TextView mLocationKegiatanTextView;

    @BindView(R.id.tv_content_detail_kegiatan)
    TextView mContentTextView;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, KesiniyukDetailActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kesiniyuk_detail);
        ButterKnife.bind(this);
        setupListener();
        setupToolbar();
        setupKegiatanData();
    }

    private void setupListener() {
        // Blank
    }

    private Kegiatan getKegiatan() {
        return (Kegiatan) getIntent().getSerializableExtra("keyKegiatan");
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayOptions(
                    ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_HOME_AS_UP
            );
            getSupportActionBar().setCustomView(R.layout.item_custom_actionbar);
        }
        setupCustomViewActionbar();
    }

    private void setupCustomViewActionbar() {
        String namePenyelenggara = getKegiatan().getPenyelenggara().getNama();
        String phonePenyelenggara = getKegiatan().getPenyelenggara().getNoTelp();
        int photoPenyelenggara = getResources().getColor(R.color.colorDarkGrey);
        if (getSupportActionBar() != null) {
            View actionbarView = getSupportActionBar().getCustomView();
            CircleImageView mIconActionbarImageView =
                    (CircleImageView) actionbarView.findViewById(R.id.iv_icon_actionbar);
            TextView mTitleActionbarTextView =
                    (TextView) actionbarView.findViewById(R.id.tv_title_actionbar);
            TextView mSubtitleActionbarTextView =
                    (TextView) actionbarView.findViewById(R.id.tv_subtitle_actionbar);
            Glide.with(this)
                    .load(photoPenyelenggara)
                    .placeholder(R.drawable.img_placeholder)
                    .centerCrop()
                    .into(mIconActionbarImageView);
            mTitleActionbarTextView.setText(namePenyelenggara);
            mSubtitleActionbarTextView.setText(phonePenyelenggara);
        }
    }

    private void setupKegiatanData() {
        String titleKegiatan = getKegiatan().getJudul();
        String photoKegiatan = getKegiatan().getFoto();
        String timeKegiatan = getKegiatan().getWaktu();
        String locationKegiatan = getKegiatan().getLokasi();
        String contentKegiatan = getKegiatan().getKonten();
        mTitleKegiatanTextView.setText(titleKegiatan);
        Glide.with(this)
                .load(photoKegiatan)
                .centerCrop()
                .into(mPhotoKegiatanImageView);
        mTimeKegiatanTextView.setText(timeKegiatan);
        mLocationKegiatanTextView.setText(locationKegiatan);
        mContentTextView.setText(contentKegiatan);
    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        if (toolbar != null) {
            int styleTitle = getResources()
                    .getIdentifier("Title.Toolbar", "style", getPackageName());
            int styleSubtitle = getResources()
                    .getIdentifier("Subtitle.Toolbar", "style", getPackageName());
            toolbar.setTitleTextAppearance(this, styleTitle);
            toolbar.setSubtitleTextAppearance(this, styleSubtitle);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_kesiniyuk, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_information:
                String message = getResources().getString(R.string.example_name);
                showSnackbar("Hallo " + message);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showToasMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showSnackbar(String message) {
        Snackbar.make(mDetailKesiniyukScrollView, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
