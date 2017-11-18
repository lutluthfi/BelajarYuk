package com.example.arifluthfiansyah.belajaryuk.ui.belajaryuk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.arifluthfiansyah.belajaryuk.BaseActivity;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pelajaran;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pengajar;
import com.example.arifluthfiansyah.belajaryuk.network.model.ResponseSendNotification;
import com.example.arifluthfiansyah.belajaryuk.network.rest.ApiClient;
import com.example.arifluthfiansyah.belajaryuk.ui.pelajaran.PelajaranFragmentDialog;
import com.example.arifluthfiansyah.belajaryuk.ui.util.FormatUtilTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class BelajaryukOrderDetailActivity extends BaseActivity implements
        PelajaranFragmentDialog.PelajaranFragmentListener {

    private static final String TAG = BelajaryukOrderDetailActivity.class.getSimpleName();
    private int currentSession = 1;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.belajaryuk_order_detail_content)
    LinearLayout mBelajaryukOrderConfirmLayout;
    @BindView(R.id.tv_course)
    TextView mCourseTextView;
    @BindView(R.id.tv_add_desc_course)
    TextView mAddDescCourseTextView;
    @BindView(R.id.et_desc_course)
    EditText mDescCourseEditText;
    @BindView(R.id.tv_session)
    TextView mSessionTextView;
    @BindView(R.id.tv_location)
    TextView mLocationTextView;
    @BindView(R.id.rb_payment_method_cash)
    RadioButton mPaymentCashRadioButton;
    @BindView(R.id.rb_payment_method_transfer)
    RadioButton mPaymentTransferRadioButton;
    @BindView(R.id.tv_payment_cash_price)
    TextView mPaymentCashTextView;
    @BindView(R.id.tv_payment_transfer_price)
    TextView mPaymentTransferView;
    @BindView(R.id.tv_name_pengajar)
    TextView mNamePengajarTextView;
    @BindView(R.id.tv_price_pengajar)
    TextView mPricePengajarTextView;
    @BindView(R.id.tv_session_total)
    TextView mSessionTotalTextView;
    @BindView(R.id.tv_price_total)
    TextView mPriceTotalTextView;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public static Intent getStartIntent(Context context) {
        return new Intent(context, BelajaryukOrderDetailActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belajaryuk_order_detail);
        ButterKnife.bind(this);
        setupToolbar();
        setupPrefixData();
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

    private void setupPrefixData() {
        String name = getPengajarData().getNama();
        int session = Integer.parseInt(mSessionTotalTextView.getText().toString());
        String priceTotal = FormatUtilTextView.currencyID().format(getPengajarData().getTarif() * session);
        mNamePengajarTextView.setText(name);
        mPricePengajarTextView.setText(String.valueOf(getPengajarData().getTarif()));
        mPaymentCashTextView.setText(FormatUtilTextView.currencyID().format(getPengajarData().getTarif()));
        mPaymentTransferView.setText(FormatUtilTextView.currencyID().format(getTransferPrice()));
        mPriceTotalTextView.setText(priceTotal);
    }

    private int getTransferPrice() {
        int chargeTransfer = getPengajarData().getTarif() * 10 / 100;
        return getPengajarData().getTarif() + chargeTransfer;
    }

    @OnClick(R.id.tv_change_course)
    public void changeCourse(View view) {
        PelajaranFragmentDialog fd = PelajaranFragmentDialog.newInstance(this);
        fd.show(getSupportFragmentManager(), "Content");
    }

    @OnClick(R.id.tv_add_desc_course)
    public void addDescriptionCourse(View view) {
        mAddDescCourseTextView.setVisibility(View.GONE);
        mDescCourseEditText.setVisibility(View.VISIBLE);
        mDescCourseEditText.requestFocus();
    }

    @OnClick(R.id.btn_increase_session)
    public void increaseSession(View view) {
        ++currentSession;
        mSessionTextView.setText(String.valueOf(currentSession));
        mSessionTotalTextView.setText(String.valueOf(currentSession));
    }

    @OnClick(R.id.btn_decrease_session)
    public void decreaseSession(View view) {
        --currentSession;
        mSessionTextView.setText(String.valueOf(currentSession));
        mSessionTotalTextView.setText(String.valueOf(currentSession));
    }

    // Masih belom bisa
    @OnClick(R.id.tv_change_location)
    public void changeLocation(View view) {
        showSnackbar(mBelajaryukOrderConfirmLayout, "Under construction");
    }

    // Masih belom bisa
    @OnClick(R.id.tv_add_inform_location)
    public void addInformationLocation(View view) {
        showSnackbar(mBelajaryukOrderConfirmLayout, "Under construction");
    }

    @OnCheckedChanged({R.id.rb_payment_method_cash, R.id.rb_payment_method_transfer})
    public void changePaymentMethod(CompoundButton button, boolean checked) {
        int id = button.getId();
        switch (id) {
            case R.id.rb_payment_method_cash:
                if (checked) {
                    mPaymentTransferRadioButton.setChecked(false);
                    mPricePengajarTextView.setText(String.valueOf(getPengajarData().getTarif()));
                }
                break;
            case R.id.rb_payment_method_transfer:
                if (checked) {
                    mPaymentCashRadioButton.setChecked(false);
                    mPricePengajarTextView.setText(String.valueOf(getTransferPrice()));
                }
                break;
        }
    }

    @OnClick(R.id.btn_order_pengajar)
    public void doOrderPengajar(View view) {
        String id = String.valueOf(getPengajarData().getId());
        String course = mCourseTextView.getText().toString();
        String description = mDescCourseEditText.getText().toString();
        int session = Integer.parseInt(mSessionTotalTextView.getText().toString());
        if (mDescCourseEditText.getText() != null && mCourseTextView.getText() != null) {
            doStoringPesanPengajarData(id, course, description, session);
        } else {
            doStoringPesanPengajarData(id, "Matematika", "Belajar yuk", session);
        }
    }

    // Get price total from multiple between price and session
    @OnTextChanged({R.id.tv_session, R.id.tv_price_pengajar})
    public void onTextChanged(CharSequence text) {
        int price = Integer.parseInt(mPricePengajarTextView.getText().toString());
        int session = Integer.parseInt(mSessionTotalTextView.getText().toString());
        int priceTotal = price * session;
        mPriceTotalTextView.setText(FormatUtilTextView.currencyID().format(priceTotal));
    }

    @Override
    public void onPelajaranItemClick(Pelajaran pelajaran) {
        showSnackbar(mBelajaryukOrderConfirmLayout, "Memilih " + pelajaran.getNama());
        mCourseTextView.setText(pelajaran.getNama());
    }

    //TODO This params still dummy
    private void doStoringPesanPengajarData(String id, String pelajaran, String keterangan, int sesi) {
        mCompositeDisposable.add(ApiClient.get(this)
                .postOrderPengajarApiCall(getAuthorizationKey(), id, pelajaran, keterangan, sesi)
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
                printLog(TAG, String.valueOf(responseSendNotification.getRecipients()));
            }

            @Override
            public void onError(Throwable e) {
                printLog(TAG, e.getMessage());
            }

            @Override
            public void onComplete() {
                Intent intent = BelajaryukOrderWaitingActivity.getStartIntent(BelajaryukOrderDetailActivity.this);
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
