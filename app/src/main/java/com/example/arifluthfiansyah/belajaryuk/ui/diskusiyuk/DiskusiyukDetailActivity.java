package com.example.arifluthfiansyah.belajaryuk.ui.diskusiyuk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.arifluthfiansyah.belajaryuk.BaseActivity;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pertanyaan;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Arif Luthfiansyah on 12/11/2017.
 */

//TODO terakhir rapihin tampilan diskusi detail activity, belum bisa post jawaban
public class DiskusiyukDetailActivity extends BaseActivity implements
        DiskusiyukDetailAdapter.DiskusiyukDetailListener {

    private static final String TAG = DiskusiyukDetailActivity.class.getSimpleName();

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.tv_title_pertanyaan) TextView mTitlePertanyaanTextView;
    @BindView(R.id.tv_course_pertanyaan) TextView mCoursePertanyaanTextView;
    @BindView(R.id.rv_diskusiyuk_detail) RecyclerView mDiskusiyukDetailRecyclerView;
    @BindView(R.id.et_answer_pertanyaan) EditText mAnswerPertanyaan;
    @BindView(R.id.btn_answer_pertanyaan) ImageButton mAnswerPertanyaanButton;

    private DiskusiyukDetailAdapter mDiskusiyukDetailAdapter;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public static Intent getStartIntent(Context context) {
        return new Intent(context, DiskusiyukDetailActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diskusiyuk_detail);
        ButterKnife.bind(this);
        setupToolbar();
        setupPertanyaanData();
        setupRecyclerView();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        String title = getPertanyaanData().getUser().getNama();
        String subtitle = getPertanyaanData().getCreatedAt();
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

    private void setupRecyclerView() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDiskusiyukDetailRecyclerView.setHasFixedSize(true);
        mDiskusiyukDetailRecyclerView.setNestedScrollingEnabled(false);
        mDiskusiyukDetailRecyclerView.setLayoutManager(mLayoutManager);
        mDiskusiyukDetailAdapter = new DiskusiyukDetailAdapter(this);
        mDiskusiyukDetailRecyclerView.setAdapter(mDiskusiyukDetailAdapter);
        mDiskusiyukDetailAdapter.addJawabans(getPertanyaanData().getJawabans());
    }

    private Pertanyaan getPertanyaanData() {
        return (Pertanyaan) getIntent().getSerializableExtra("keyPertanyaan");
    }

    private void setupPertanyaanData() {
        mTitlePertanyaanTextView.setText(getPertanyaanData().getJudul());
        mCoursePertanyaanTextView.setText(getPertanyaanData().getPelajaran());
    }

    @Override
    public void openMenuItemJawaban() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
        mDiskusiyukDetailAdapter.clearJawabans();
    }
}
