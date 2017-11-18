package com.example.arifluthfiansyah.belajaryuk.ui.profile;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.data.AppPreferencesHelper;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kabupaten;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kabupatens;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kecamatan;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kecamatans;
import com.example.arifluthfiansyah.belajaryuk.network.model.User;
import com.example.arifluthfiansyah.belajaryuk.network.rest.ApiClient;
import com.mlsdev.rximagepicker.RxImageConverters;
import com.mlsdev.rximagepicker.RxImagePicker;
import com.mlsdev.rximagepicker.Sources;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.thefinestartist.utils.content.ContextUtil.getExternalFilesDir;

/**
 * Created by Arif Luthfiansyah on 20/09/2017.
 */

// Class of user profile who are logged in
public class ProfileFragment extends Fragment {

    //TODO Setup if use whose login is Pengajar
    private static final String TAG = ProfileFragment.class.getSimpleName();
    private Context mContext;
    private Unbinder mUnbinder;

    @BindView(R.id.form_layout) LinearLayout mFormLayout;
    @BindView(R.id.iv_photo_user) CircleImageView mPhotoUserImageView;
    @BindView(R.id.tv_change_photo) TextView mChangePhotoTextView;
    @BindView(R.id.et_name_user) EditText mNameUserEditText;
    @BindView(R.id.et_handphone_user) EditText mHandphoneUserEditText;
    @BindView(R.id.sp_city) Spinner mCitySpinner;
    @BindView(R.id.sp_district) Spinner mDistrictSpinner;
    @BindView(R.id.et_address_user) EditText mAddressUserEditText;
    @BindView(R.id.btn_save_profile_user) Button mSaveProfileUserButton;
    @BindView(R.id.pb_fragment_profile) ProgressBar mProgressbar;

    private ProfileFragmentListener mListener;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private ArrayAdapter<Kabupaten> mKabupatenSpinnerAdapter;
    private ArrayAdapter<Kecamatan> mKecamatanSpinnerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setupTitleFragment();
        setHasOptionsMenu(true);
        doFetchingUserData();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_logout:
                mListener.doLogout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupTitleFragment() {
        String titleFragment = mContext.getResources()
                .getString(R.string.title_fragment_profile);
        if (getActivity() != null) {
            getActivity().setTitle(titleFragment);
        }
    }

    private String getAuthorizationKey() {
        Log.d(TAG, "Token: " + AppPreferencesHelper.with(mContext).getUserAuthorization());
        return AppPreferencesHelper.with(mContext).getUserAuthorization();
    }

    private String getUserCityKey() {
        return AppPreferencesHelper.with(mContext).getUserCity();
    }

    // Guess got bug here
    private void doFetchingUserData() {
        showProgress(true);
        mCompositeDisposable.add(ApiClient.get(mContext)
                .getUserApiCall(getAuthorizationKey())
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
                Glide.with(mContext).load(user.getFoto()).asBitmap().centerCrop().into(mPhotoUserImageView);
                mNameUserEditText.setText(user.getNama());
                mHandphoneUserEditText.setText(user.getNoTelp());
                mAddressUserEditText.setText(user.getAlamat());
                doFetchingKabupatensData();
                doFetchingKecamatansData();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                String message = getResources().getString(R.string.error_failed_fetch_data);
                showToastMessage(message);
                showProgessbar(false);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Complete Fetching User");
                showProgress(false);
            }
        };
    }

    private void doFetchingKabupatensData() {
        mCompositeDisposable.add(ApiClient.get(mContext)
                .getKabupatenApiCall()
                .onBackpressureDrop()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserverKabupatens())
        );
    }

    //TODO Kabupaten and Kecamatan spinner selected item still random
    private DisposableObserver<Kabupatens> getObserverKabupatens() {
        return new DisposableObserver<Kabupatens>() {
            @Override
            public void onNext(@NonNull Kabupatens kabupatens) {
                mKabupatenSpinnerAdapter = new ArrayAdapter<Kabupaten>(
                        mContext, android.R.layout.simple_spinner_item, kabupatens.getKabupatens()
                );
                mKabupatenSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                mCitySpinner.setAdapter(mKabupatenSpinnerAdapter);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                String message = getResources().getString(R.string.error_failed_fetch_data);
                showToastMessage(message);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Complete fetching kabupatens");
            }
        };
    }

    private void doFetchingKecamatansData() {
        mCompositeDisposable.add(ApiClient.get(mContext)
                .getKecamatanApiCall()
                .onBackpressureDrop()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserverKecamatans())
        );
    }

    private DisposableObserver<Kecamatans> getObserverKecamatans() {
        return new DisposableObserver<Kecamatans>() {
            @Override
            public void onNext(@NonNull Kecamatans kecamatans) {
                mKecamatanSpinnerAdapter = new ArrayAdapter<Kecamatan>(
                        mContext, android.R.layout.simple_spinner_item, kecamatans.getKecamatans()
                );
                mKecamatanSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                mDistrictSpinner.setAdapter(mKecamatanSpinnerAdapter);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                String message = getResources().getString(R.string.error_failed_fetch_data);
                showToastMessage(message);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Complete fetching kecamatans");
            }
        };
    }

    @OnClick(R.id.tv_change_photo)
    public void doChangePhoto(View view) {
        RxImagePicker.with(mContext)
                .requestImage(Sources.GALLERY)
                .flatMap(new Function<Uri, ObservableSource<File>>() {
                    @Override
                    public ObservableSource<File> apply(@NonNull Uri uri) throws Exception {
                        return RxImageConverters.uriToFile(mContext, uri, createTempFile());
                    }
                })
                .subscribe(new Observer<File>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscibeImagePicker");
                    }

                    @Override
                    public void onNext(@NonNull File f) {
                        Log.d(TAG, f.toString());
                        onImagePicked(f);
                        mPhotoUserImageView.setTag(f.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, e.getMessage());
                        showToastMessage(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onCompleteImagePicker");
                    }
                });
    }

    private void onImagePicked(Object result) {
        Log.d(TAG, result.toString());
        showToastMessage("Success!");
        if (result instanceof Bitmap) {
            mPhotoUserImageView.setImageBitmap((Bitmap) result);
        } else {
            Glide.with(mContext).load(result).asBitmap().into(mPhotoUserImageView);
        }
    }

    private File createTempFile() {
        return new File(getExternalFilesDir(
                Environment.DIRECTORY_PICTURES),
                System.currentTimeMillis() + "_img_belajaryuk.jpeg");
    }

    //TODO Not yet to upadate user datas
    @OnClick(R.id.btn_save_profile_user)
    public void doSaveProfileUser(View view) {
        showToastMessage("Under construction");
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
        mCompositeDisposable.clear();
    }

    public interface ProfileFragmentListener {
        void doLogout();
    }
}
