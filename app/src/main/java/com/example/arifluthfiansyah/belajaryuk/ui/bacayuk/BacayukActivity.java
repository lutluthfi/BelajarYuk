package com.example.arifluthfiansyah.belajaryuk.ui.bacayuk;

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

import com.example.arifluthfiansyah.belajaryuk.BaseActivity;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Berita;
import com.example.arifluthfiansyah.belajaryuk.network.model.Beritas;
import com.example.arifluthfiansyah.belajaryuk.network.rest.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class BacayukActivity extends BaseActivity implements
        SwipeRefreshLayout.OnRefreshListener, BacayukAdapter.BacayukListener {

    private static final String TAG = BacayukActivity.class.getSimpleName();
    private final int itemGridCount = 2; // Var for span of grid
    private int currentPage = 1; // Var for pagination

    @BindView(R.id.bacayuk_content) SwipeRefreshLayout mBacayukRefreshLayout;
    @BindView(R.id.rv_bacayuk) RecyclerView mBacayukRecyclerView;

    private BacayukAdapter mBacayukAdapter;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public static Intent getStartIntent(Context context) {
        return new Intent(context, BacayukActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bacayuk);
        ButterKnife.bind(this);
        setupToolbar();
        setupListerner();
        setupRecyclerView();
        doFetchingBeritasData();
    }

    private void setupListerner() {
        mBacayukRefreshLayout.setOnRefreshListener(this);
    }

    private void setupToolbar() {
        String title = getResources().getString(R.string.title_feature_bacayuk);
        setTitle(title);
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
        mBacayukRecyclerView.setHasFixedSize(true);
        mBacayukRecyclerView.setLayoutManager(mLayoutManager);
        mBacayukAdapter = new BacayukAdapter(this);
        mBacayukRecyclerView.setAdapter(mBacayukAdapter);
    }

    private void doFetchingBeritasData() {
        mBacayukRefreshLayout.setRefreshing(true);
        mCompositeDisposable.add(ApiClient.get(this)
                .getBeritaApiCall(1)
                .onBackpressureDrop()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserverBeritas())
        );
    }

    private DisposableObserver<Beritas> getObserverBeritas() {
        return new DisposableObserver<Beritas>() {
            @Override
            public void onNext(@NonNull Beritas beritas) {
                mBacayukAdapter.addBeritas(beritas);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                showToastMessage(e.getMessage());
                finish();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Complete Fetching Berita");
                mBacayukRefreshLayout.setRefreshing(false);
            }
        };
    }

    @Override
    public void onBeritaItemClick(Berita berita) {
        Intent intent = BacayukDetailActivity.getStartIntent(this);
        intent.putExtra("keyBerita", berita);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        mBacayukAdapter.clearBeritas();
        doFetchingBeritasData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
        mBacayukAdapter.clearBeritas();
    }
}
