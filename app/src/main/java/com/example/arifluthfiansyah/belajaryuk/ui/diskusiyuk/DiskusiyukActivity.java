package com.example.arifluthfiansyah.belajaryuk.ui.diskusiyuk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.arifluthfiansyah.belajaryuk.BaseActivity;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.data.AppPreferencesHelper;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pertanyaan;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pertanyaans;
import com.example.arifluthfiansyah.belajaryuk.network.rest.ApiClient;
import com.example.arifluthfiansyah.belajaryuk.ui.pelajaran.PelajaranFragmentDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Arif Luthfiansyah on 11/11/2017.
 */

public class DiskusiyukActivity extends BaseActivity implements
        SwipeRefreshLayout.OnRefreshListener, DiskusiyukAdapter.DiskusiyukListener {

    private static final String TAG = DiskusiyukActivity.class.getSimpleName();
    private int currentPage = 1; // Var for pagination

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.diskusiyuk_content) SwipeRefreshLayout mDiskusiyukRefreshLayout;
    @BindView(R.id.rv_diskusiyuk) RecyclerView mDiskusiyukRecyclerView;

    private DiskusiyukAdapter mDiskusiyukAdapter;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public static Intent getStartIntent(Context context) {
        return new Intent(context, DiskusiyukActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diskusiyuk);
        ButterKnife.bind(this);
        setupListener();
        setupToolbar();
        setupRecyclerView();
        doFetchingPertanyaanData();
    }

    private void setupListener() {
        mDiskusiyukRefreshLayout.setOnRefreshListener(this);
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        String title = getResources().getString(R.string.prompt_course);
        String subtitle = AppPreferencesHelper.with(this).getUserCourse();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setSubtitle(subtitle);
        }
    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setContentInsetStartWithNavigation(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_diskusiyuk, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_search:
                PelajaranFragmentDialog fd = new PelajaranFragmentDialog();
                fd.show(getSupportFragmentManager(), "Content");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDiskusiyukRecyclerView.setHasFixedSize(true);
        mDiskusiyukRecyclerView.setLayoutManager(mLayoutManager);
        mDiskusiyukAdapter = new DiskusiyukAdapter(this);
        mDiskusiyukRecyclerView.setAdapter(mDiskusiyukAdapter);
    }

    private void doFetchingPertanyaanData() {
        mDiskusiyukRefreshLayout.setRefreshing(true);
        mCompositeDisposable.add(ApiClient.get(this)
                .getPertanyaanApiCall("",1)
                .onBackpressureDrop()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserverPertanyaans())
        );
    }

    private DisposableObserver<Pertanyaans> getObserverPertanyaans() {
        return new DisposableObserver<Pertanyaans>() {
            @Override
            public void onNext(@NonNull Pertanyaans pertanyaans) {
                mDiskusiyukAdapter.addPertanyaans(pertanyaans);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                showToastMessage(e.getMessage());
                finish();
            }

            @Override
            public void onComplete() {
                printLog(TAG, "Complete fetching pertanyaans");
                mDiskusiyukRefreshLayout.setRefreshing(false);
            }
        };
    }

    @Override
    public void onRefresh() {
        mDiskusiyukAdapter.clearPertanyaans();
        doFetchingPertanyaanData();
    }

    @Override
    public void onPertanyaanItemClick(Pertanyaan pertanyaan) {
        Intent intent = DiskusiyukDetailActivity.getStartIntent(this);
        intent.putExtra("keyPertanyaan", pertanyaan);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
        mDiskusiyukAdapter.clearPertanyaans();
    }
}
