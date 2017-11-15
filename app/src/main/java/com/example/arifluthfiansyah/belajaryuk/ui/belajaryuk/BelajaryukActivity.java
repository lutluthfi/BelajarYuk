package com.example.arifluthfiansyah.belajaryuk.ui.belajaryuk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.arifluthfiansyah.belajaryuk.BaseActivity;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.data.AppPreferencesHelper;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pengajar;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pengajars;
import com.example.arifluthfiansyah.belajaryuk.network.rest.ApiClient;
import com.thefinestartist.Base;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class BelajaryukActivity extends BaseActivity implements
        SwipeRefreshLayout.OnRefreshListener, BelajaryukAdapter.BelajaryukListener {

    private static final String TAG = BelajaryukActivity.class.getSimpleName();

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.belajaryuk_content) SwipeRefreshLayout mBelajaryukRefreshLayout;
    @BindView(R.id.rv_belajaryuk) RecyclerView mBelajaryukRecyclerView;

    private BelajaryukAdapter mBelajaryukAdapter;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public static Intent getStartIntent(Context context) {
        return new Intent(context, BelajaryukActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belajaryuk);
        ButterKnife.bind(this);
        setupToolbar();
        setupListener();
        setupRecyclerView();
        doFetchingPengajarData();
    }

    private void setupListener() {
        mBelajaryukRefreshLayout.setOnRefreshListener(this);
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        String title = getUserCity();
        String subtitle = getResources().getString(R.string.example_course);
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
        getMenuInflater().inflate(R.menu.menu_belajaryuk, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_search:
                String message = getResources().getString(R.string.example_name);
                showSnackbar(mBelajaryukRefreshLayout,"Search " + message);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView() {
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        mBelajaryukRecyclerView.setHasFixedSize(true);
        mBelajaryukRecyclerView.setLayoutManager(mLayoutManager);
        mBelajaryukAdapter = new BelajaryukAdapter(this);
        mBelajaryukRecyclerView.setAdapter(mBelajaryukAdapter);
    }

    private String getUserCity() {
        return AppPreferencesHelper.with(this).getUserCity();
    }

    private void doFetchingPengajarData() {
        onRefreshing();
        mCompositeDisposable.add(ApiClient.get(this)
                .getPengajarApiCall(getUserCity(), 1)
                .onBackpressureDrop()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserverPengajars())
        );
    }

    private DisposableObserver<Pengajars> getObserverPengajars() {
        return new DisposableObserver<Pengajars>() {
            @Override
            public void onNext(@NonNull Pengajars pengajars) {
                mBelajaryukAdapter.addPengajars(pengajars);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                showToastMessage(e.getMessage());
                finish();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Complete Fetching Pengajar");
                stopRefreshing();
            }
        };
    }

    @Override
    public void onPengajarItemClick(Pengajar pengajar) {
        BelajaryukProfileFragmentDialog fragmentDialog =
                BelajaryukProfileFragmentDialog.newInstance(pengajar);
        fragmentDialog.show(getSupportFragmentManager(), "Content");
    }

    @Override
    public void onRefresh() {
        mBelajaryukAdapter.clearPengajars();
        doFetchingPengajarData();
    }

    private void onRefreshing() {
        mBelajaryukRefreshLayout.setRefreshing(true);
    }

    private void stopRefreshing() {
        mBelajaryukRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
        mBelajaryukAdapter.clearPengajars();
    }
}
