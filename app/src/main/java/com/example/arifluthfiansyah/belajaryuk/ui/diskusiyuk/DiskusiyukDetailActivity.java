package com.example.arifluthfiansyah.belajaryuk.ui.diskusiyuk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Jawabans;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kegiatans;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pertanyaan;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pertanyaans;
import com.example.arifluthfiansyah.belajaryuk.network.rest.ApiClient;
import com.example.arifluthfiansyah.belajaryuk.ui.kesiniyuk.KesiniyukAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Arif Luthfiansyah on 12/11/2017.
 */

public class DiskusiyukDetailActivity extends AppCompatActivity implements
        SwipeRefreshLayout.OnRefreshListener, DiskusiyukDetailAdapter.DiskusiyukDetailListener {

    //TODO Terakhir sampai di sini, nested scroll masih rusak
    private static final String TAG = DiskusiyukDetailActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.diskusiyuk_detail_content)
    SwipeRefreshLayout mDiskusiyukDetailContent;

    @BindView(R.id.tv_title_pertanyaan)
    TextView mTitlePertanyaanTextView;

    @BindView(R.id.tv_name_user)
    TextView mNameUserTextView;

    @BindView(R.id.rv_diskusiyuk_detail)
    RecyclerView mDiskusiyukDetailRecyclerView;

    @BindView(R.id.btn_answer_pertanyaan)
    Button mAnswerPertanyaanButton;

    private LinearLayoutManager mLayoutManager;
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
        setupListener();
        setupRecyclerView();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        String title = getResources().getString(R.string.title_feature_kesiniyuk);
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

    private void setupListener() {
        mDiskusiyukDetailContent.setOnRefreshListener(this);
    }

    private void setupRecyclerView() {
        setRefreshing(true);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDiskusiyukDetailRecyclerView.setHasFixedSize(true);
        mDiskusiyukDetailRecyclerView.setNestedScrollingEnabled(true);
        mDiskusiyukDetailRecyclerView.setLayoutManager(mLayoutManager);
        mDiskusiyukDetailAdapter = new DiskusiyukDetailAdapter(this);
        mDiskusiyukDetailRecyclerView.setAdapter(mDiskusiyukDetailAdapter);
        mDiskusiyukDetailAdapter.addJawabans(getJawabansData());
        setRefreshing(false);
    }

    private Jawabans getJawabansData() {
        return (Jawabans) getIntent().getSerializableExtra("keyJawabans");
    }

    @Override
    public void onRefresh() {
        mDiskusiyukDetailAdapter.clearJawabans();
        mDiskusiyukDetailAdapter.addJawabans(getJawabansData());
    }

    @Override
    public void openMenuItemJawaban() {

    }

    private void setRefreshing(boolean refresh) {
        mDiskusiyukDetailContent.setRefreshing(refresh);
    }

    private void showToasMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showSnackbar(String message) {
        Snackbar.make(mDiskusiyukDetailContent, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
        mDiskusiyukDetailAdapter.clearJawabans();
    }
}
