package com.example.arifluthfiansyah.belajaryuk.ui.donasiyuk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kampanye;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kampanyes;
import com.example.arifluthfiansyah.belajaryuk.network.rest.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DonasiyukActivity extends AppCompatActivity implements
        SwipeRefreshLayout.OnRefreshListener, DonasiyukAdapter.DonasiyukListener {

    //TODO Belom ada detail donasiyuk

    private static final String TAG = DonasiyukActivity.class.getSimpleName();
    private int currentPage = 1;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.donasiyuk_content)
    SwipeRefreshLayout mDonasiyukRefreshLayout;

    @BindView(R.id.rv_donasiyuk)
    RecyclerView mDonasiyukRecyclerView;

    private DonasiyukAdapter mDonasiyukAdapter;
    private LinearLayoutManager mLayoutManager;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public static Intent getStartIntent(Context context) {
        return new Intent(context, DonasiyukActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasiyuk);
        ButterKnife.bind(this);
        setupToolbar();
        setupListener();
        setupRecyclerView();
        doFetchingKampanyeData();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        String title = getResources().getString(R.string.title_feature_donasiyuk);
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
        mDonasiyukRefreshLayout.setOnRefreshListener(this);
    }

    private void setupRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDonasiyukRecyclerView.setHasFixedSize(true);
        mDonasiyukRecyclerView.setLayoutManager(mLayoutManager);
        mDonasiyukAdapter = new DonasiyukAdapter(this);
        mDonasiyukRecyclerView.setAdapter(mDonasiyukAdapter);
    }

    private void doFetchingKampanyeData() {
        onRefreshing();
        mCompositeDisposable.add(ApiClient.get(this)
                .getKampanyeApiCall(currentPage)
                .onBackpressureDrop()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserverKampanyes())
        );
    }

    private DisposableObserver<Kampanyes> getObserverKampanyes() {
        return new DisposableObserver<Kampanyes>() {
            @Override
            public void onNext(@NonNull Kampanyes kampanyes) {
                mDonasiyukAdapter.addKampanyes(kampanyes);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                showToasMessage(e.getMessage());
                finish();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Complete Fetching Kampanye");
                stopRefreshing();
            }
        };
    }

    @Override
    public void onKampanyeItemClick(Kampanye kampanye) {

    }

    @Override
    public void onRefresh() {
        mDonasiyukAdapter.clearKampanye();
        doFetchingKampanyeData();
    }

    private void onRefreshing() {
        mDonasiyukRefreshLayout.setRefreshing(true);
    }

    private void stopRefreshing() {
        mDonasiyukRefreshLayout.setRefreshing(false);
    }

    private void showToasMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showSnackbar(String message) {
        Snackbar.make(mDonasiyukRefreshLayout, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
        mDonasiyukAdapter.clearKampanye();
    }
}
