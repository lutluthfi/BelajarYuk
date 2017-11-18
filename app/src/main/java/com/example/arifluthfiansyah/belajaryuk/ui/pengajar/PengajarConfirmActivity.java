package com.example.arifluthfiansyah.belajaryuk.ui.pengajar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arifluthfiansyah.belajaryuk.BaseActivity;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.User;
import com.example.arifluthfiansyah.belajaryuk.network.model.Users;
import com.example.arifluthfiansyah.belajaryuk.network.rest.ApiClient;
import com.example.arifluthfiansyah.belajaryuk.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Arif Luthfiansyah on 16/11/2017.
 */

//TODO Don't forget to change action button if accept or decline order. Send notif to pelajar
public class PengajarConfirmActivity extends BaseActivity {

    //TODO Terakhir sampai di sini
    private static final String TAG = PengajarConfirmActivity.class.getSimpleName();

    @BindView(R.id.iv_photo_user) CircleImageView mPhotoUserImageView;
    @BindView(R.id.tv_name_user) TextView mNameUserTextView;
    @BindView(R.id.tv_bio_user) TextView mBioUserTextView;
    @BindView(R.id.tv_course_order) TextView mCourseOrderTextView;
    @BindView(R.id.tv_course_desc) TextView mCourseOrderDescTextView;
    @BindView(R.id.tv_session_order) TextView mSessionOrderTextView;
    @BindView(R.id.tv_location_user) TextView mLocationUserTextView;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public static Intent getStartIntent(Context context) {
        return new Intent(context, PengajarConfirmActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajar_confirm);
        ButterKnife.bind(this);
        doFetchingUserData();
        setupPrefixData();
    }

    private void setupPrefixData() {
        String title = this.getIntent().getStringExtra("titleKey");
        String course = this.getIntent().getStringExtra("courseKey");
        String description = this.getIntent().getStringExtra("descriptionKey");
        int session = this.getIntent().getIntExtra("sessionKey", 1);
        int priceTotal = this.getIntent().getIntExtra("priceTotalKey", 1);
        mCourseOrderTextView.setText(course);
        mCourseOrderDescTextView.setText(description);
        mSessionOrderTextView.setText(String.valueOf(session));
    }

    private int getUserId() {
        return this.getIntent().getIntExtra("userIdKey", 1);
    }

    @OnClick(R.id.btn_accept_order)
    public void doAcceptOrder(View view) {
        Intent intent = MainActivity.getStartIntent(this);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_decline_order)
    public void doDeclineOrder(View view) {
        Intent intent = MainActivity.getStartIntent(this);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void doFetchingUserData() {
        mCompositeDisposable.add(ApiClient.get(this)
                .getUserApiCall(getAuthorizationKey(), getUserId())
                .onBackpressureDrop()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserverUser())
        );
    }

    private DisposableObserver<User> getObserverUser() {
        return new DisposableObserver<User>() {
            @Override
            public void onNext(@NonNull User user) {
                printLog(TAG, user.getNama());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                String message = getResources().getString(R.string.error_failed_fetch_data);
                showToastMessage(message);
            }

            @Override
            public void onComplete() {
                printLog(TAG, "Complete fetching user");
            }
        };
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        // Disable back button, so pengajar can't ignore the request order from pelajar
    }
}
