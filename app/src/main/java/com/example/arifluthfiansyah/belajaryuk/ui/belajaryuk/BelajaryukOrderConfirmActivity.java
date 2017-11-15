package com.example.arifluthfiansyah.belajaryuk.ui.belajaryuk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.arifluthfiansyah.belajaryuk.BaseActivity;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pelajaran;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pengajar;
import com.example.arifluthfiansyah.belajaryuk.network.model.ResponseSendNotification;
import com.example.arifluthfiansyah.belajaryuk.network.rest.ApiClient;
import com.example.arifluthfiansyah.belajaryuk.ui.pelajaran.PelajaranFragmentDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class BelajaryukOrderConfirmActivity extends BaseActivity implements
        PelajaranFragmentDialog.PelajaranFragmentListener{

    private static final String TAG = BelajaryukOrderConfirmActivity.class.getSimpleName();

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.belajaryuk_order_confirm_content) LinearLayout mBelajaryukOrderConfirmLayout;
    @BindView(R.id.tv_courses) TextView mCoursesTextView;
    @BindView(R.id.tv_session) TextView mSessionTextView;
    @BindView(R.id.tv_location) TextView mLocationTextView;
    @BindView(R.id.tv_payment_cash_price) TextView mPaymentCashTextView;
    @BindView(R.id.tv_payment_transfer_price) TextView mPaymentTransferView;
    @BindView(R.id.tv_name_pengajar) TextView mNamePengajarTextView;
    @BindView(R.id.tv_price_pengajar) TextView mPricePengajarTextView;
    @BindView(R.id.tv_session_total) TextView mSessionTotalTextView;
    @BindView(R.id.tv_price_total) TextView mPriceTotalTextView;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public static Intent getStartIntent(Context context) {
        return new Intent(context, BelajaryukOrderConfirmActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belajaryuk_order_confirm);
        ButterKnife.bind(this);
        setupToolbar();
        setupPengajarData();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        String title = getResources().getString(R.string.title_feature_belajaryuk_order);
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

    private Pengajar getPengajarData() {
        return (Pengajar) getIntent().getSerializableExtra("keyPengajar");
    }

    private void setupPengajarData() {
        String name = getPengajarData().getNama();
        String price = "Rp. " + Integer.toString(getPengajarData().getTarif());
        mNamePengajarTextView.setText(name);
        mPricePengajarTextView.setText(price);
    }

    @OnClick(R.id.tv_change_courses)
    public void changeCourse(View view) {
        PelajaranFragmentDialog fd = PelajaranFragmentDialog.newInstance(this);
        fd.show(getSupportFragmentManager(), "Content");
    }

    @OnClick(R.id.tv_add_desc_courses)
    public void addDescriptionCourse(View view) {
        showSnackbar(mBelajaryukOrderConfirmLayout, "Under construction");
    }

    @OnClick(R.id.tv_change_session)
    public void changeSession(View view) {
        showSnackbar(mBelajaryukOrderConfirmLayout, "Under construction");
    }

    @OnClick(R.id.tv_change_location)
    public void changeLocation(View view) {
        showSnackbar(mBelajaryukOrderConfirmLayout, "Under construction");
    }

    @OnClick(R.id.tv_add_inform_location)
    public void addInformationLocation(View view) {
        showSnackbar(mBelajaryukOrderConfirmLayout, "Under construction");
    }

    @OnClick(R.id.btn_order_pengajar)
    public void doOrderPengajar(View view) {
        doStoringPesanPengajarData("1", "keterangan", 3);
    }

    @Override
    public void onPelajaranItemClick(Pelajaran pelajaran) {
        showSnackbar(mBelajaryukOrderConfirmLayout, pelajaran.getNama());
    }

    //TODO This params still dummy
    private void doStoringPesanPengajarData(String id, String keterangan, int sesi) {
        mCompositeDisposable.add(
                ApiClient.get(this)
                        .postPesanPengajarApiCall(getAuthorizationKey(), id, keterangan, sesi)
                        .onBackpressureDrop()
                        .toObservable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(getObserverResponse())
        );
    }

    private DisposableObserver<ResponseSendNotification> getObserverResponse() {
        return new DisposableObserver<ResponseSendNotification>() {
            @Override
            public void onNext(ResponseSendNotification responseSendNotification) {
                printLog(TAG, responseSendNotification.getId());
            }

            @Override
            public void onError(Throwable e) {
                printLog(TAG, e.getMessage());
            }

            @Override
            public void onComplete() {
                Intent intent = BelajaryukOrderActivity.getStartIntent(BelajaryukOrderConfirmActivity.this);
                startActivity(intent);
                finish();
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}
