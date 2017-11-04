package com.example.arifluthfiansyah.belajaryuk.ui.kesiniyuk;

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
import android.widget.Toast;

import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kegiatan;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kegiatans;
import com.example.arifluthfiansyah.belajaryuk.network.rest.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class KesiniyukActivity extends AppCompatActivity implements
        SwipeRefreshLayout.OnRefreshListener, KesiniyukAdapter.KesiniyukListener {

    private static final String TAG = KesiniyukActivity.class.getSimpleName();
    private int currentPage = 1;

    @BindView(R.id.kesiniyuk_content)
    SwipeRefreshLayout mKesiniyukRefreshLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.rv_kesiniyuk)
    RecyclerView mKesiniyukRecyclerView;

    private KesiniyukAdapter mKesiniyukAdapter;
    private LinearLayoutManager mLayoutManager;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public static Intent getStartIntent(Context context) {
        return new Intent(context, KesiniyukActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kesiniyuk);
        ButterKnife.bind(this);
        setupToolbar();
        setupListener();
        setupRecyclerView();
        doFetchingKegiatansData();
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
        mKesiniyukRefreshLayout.setOnRefreshListener(this);
    }

    private void setupRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mKesiniyukRecyclerView.setHasFixedSize(true);
        mKesiniyukRecyclerView.setLayoutManager(mLayoutManager);
        mKesiniyukAdapter = new KesiniyukAdapter(this);
        mKesiniyukRecyclerView.setAdapter(mKesiniyukAdapter);
    }

    private void doFetchingKegiatansData() {
        onRefreshing();
        mCompositeDisposable.add(ApiClient.get(this)
                .getKegiatanApiCall(currentPage)
                .onBackpressureDrop()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserverKegiatans())
        );
    }

    private DisposableObserver<Kegiatans> getObserverKegiatans() {
        return new DisposableObserver<Kegiatans>() {
            @Override
            public void onNext(@NonNull Kegiatans kegiatans) {
                mKesiniyukAdapter.addKegiatans(kegiatans);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                showToasMessage(e.getMessage());
                finish();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Complete Fetching Kegiatan");
                stopRefreshing();
            }
        };
    }

    @Override
    public void openDetailKesiniyuk(Kegiatan kegiatan) {
        Intent intent = KesiniyukDetailActivity.getStartIntent(this);
        intent.putExtra("keyKegiatan", kegiatan);
        startActivity(intent);
    }

    @Override
    public void openMenuItemKegiatan(Kegiatan kegiatan) {
        KesiniyukActionFragmentDialog fragmentDialog =
                KesiniyukActionFragmentDialog.newInstance(kegiatan);
        fragmentDialog.show(getSupportFragmentManager(), "Content");
    }

    @Override
    public void onRefresh() {
        mKesiniyukAdapter.clearKegiatans();
        doFetchingKegiatansData();
    }

    private void onRefreshing() {
        mKesiniyukRefreshLayout.setRefreshing(true);
    }

    private void stopRefreshing() {
        mKesiniyukRefreshLayout.setRefreshing(false);
    }

    private void showToasMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showSnackbar(String message) {
        Snackbar.make(mKesiniyukRefreshLayout, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
        mKesiniyukAdapter.clearKegiatans();
    }
}
