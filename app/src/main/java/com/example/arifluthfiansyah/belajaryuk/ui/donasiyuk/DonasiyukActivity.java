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

import com.example.arifluthfiansyah.belajaryuk.BaseActivity;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kampanye;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kampanyes;
import com.example.arifluthfiansyah.belajaryuk.network.model.Links;
import com.example.arifluthfiansyah.belajaryuk.network.rest.ApiClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DonasiyukActivity extends BaseActivity implements
        SwipeRefreshLayout.OnRefreshListener, DonasiyukAdapter.DonasiyukListener {

    //TODO Belom ada detail donasiyuk
    private static final String TAG = DonasiyukActivity.class.getSimpleName();
    private int currentPage = 1; // Var for pagination

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.donasiyuk_content) SwipeRefreshLayout mDonasiyukRefreshLayout;
    @BindView(R.id.rv_donasiyuk) RecyclerView mDonasiyukRecyclerView;

    private DonasiyukAdapter mDonasiyukAdapter;
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
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDonasiyukRecyclerView.setHasFixedSize(true);
        mDonasiyukRecyclerView.setLayoutManager(mLayoutManager);
        mDonasiyukAdapter = new DonasiyukAdapter(this);
        mDonasiyukRecyclerView.setAdapter(mDonasiyukAdapter);
    }

    private void doFetchingKampanyeData() {
        mDonasiyukRefreshLayout.setRefreshing(true);
        mCompositeDisposable.add(ApiClient.get(this)
                .getKampanyeApiCall(1)
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
                printLog(TAG, e.getMessage());
                showToastMessage(e.getMessage());
                finish();
            }

            @Override
            public void onComplete() {
                printLog(TAG, "Complete fetching kampanyes");
                mDonasiyukRefreshLayout.setRefreshing(false);
            }
        };
    }

    @Override
    public void onKampanyeItemClick(Kampanye kampanye) {
        showSnackbar(mDonasiyukRefreshLayout, kampanye.getJudul());
    }

    @Override
    public void onRefresh() {
        mDonasiyukAdapter.clearKampanye();
        doFetchingKampanyeData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
        mDonasiyukAdapter.clearKampanye();
    }
}
