package com.example.arifluthfiansyah.belajaryuk.ui.profile;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.data.AppPreferencesHelper;
import com.example.arifluthfiansyah.belajaryuk.network.model.Passport;
import com.example.arifluthfiansyah.belajaryuk.network.model.Token;
import com.example.arifluthfiansyah.belajaryuk.network.model.User;
import com.example.arifluthfiansyah.belajaryuk.network.rest.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Arif Luthfiansyah on 20/09/2017.
 */

// Class of user profile who are logged in
public class ProfileFragment extends Fragment {

    private static final String TAG = ProfileFragment.class.getSimpleName();
    private Context mContext;
    private Unbinder mUnbinder;

    @BindView(R.id.form_layout)
    LinearLayout mFormLayout;

    @BindView(R.id.iv_photo_user)
    CircleImageView mPhotoUserImageView;

    @BindView(R.id.tv_change_photo)
    TextView mChangePhotoTextView;

    @BindView(R.id.et_name_user)
    EditText mNameUserEditText;

    @BindView(R.id.et_handphone_user)
    EditText mHandphoneUserEditText;

    @BindView(R.id.sp_province)
    Spinner mProvinceSpinner;

    @BindView(R.id.sp_city)
    Spinner mCitySpinner;

    @BindView(R.id.et_address_user)
    EditText mAddressUserEditText;

    @BindView(R.id.btn_save_profile_user)
    Button mSaveProfileUserButton;

    @BindView(R.id.btn_logout)
    Button mLogoutButton;

    @BindView(R.id.pb_fragment_profile)
    ProgressBar mProgressbar;

    private ProfileFragmentListener mListener;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setupTitleFragment();
        doFetchingUserData();
        return view;
    }

    private void setupListener(){

    }

    private void setupTitleFragment() {
        String titleFragment = mContext.getResources()
                .getString(R.string.title_fragment_profile);
        getActivity().setTitle(titleFragment);
    }

    private String getKeyUserAuthorization() {
        return AppPreferencesHelper.with(mContext).getUserAuthorization();
    }

    private void doFetchingUserData() {
        showProgress(true);
        mCompositeDisposable.add(ApiClient.get(mContext)
                .getUserApiCall(getKeyUserAuthorization())
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
                String photo = user.getFoto();
                String name = user.getNama();
                Glide.with(mContext)
                        .load(photo)
                        .centerCrop()
                        .into(mPhotoUserImageView);
                mNameUserEditText.setText(name);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                String message = getResources().getString(R.string.error_failed_fetch_data);
                showToastMessage(message);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Complete Fetching User");
                showProgress(false);
            }
        };
    }

    @OnClick(R.id.btn_logout)
    public void doLogout(View view) {
        mListener.doLogout();
    }

    private void showProgress(boolean show) {
        showProgessbar(show);
        showFormLayout(show);
    }

    private void showFormLayout(boolean show) {
        mFormLayout.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    private void showProgessbar(boolean show) {
        mProgressbar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void showToastMessage(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        mListener = (ProfileFragmentListener) activity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    public interface ProfileFragmentListener {
        void doLogout();
    }
}
