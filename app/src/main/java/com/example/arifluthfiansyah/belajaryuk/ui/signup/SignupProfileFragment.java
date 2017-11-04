package com.example.arifluthfiansyah.belajaryuk.ui.signup;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.data.model.Profile;
import com.example.arifluthfiansyah.belajaryuk.ui.login.LoginActivity;
import com.mlsdev.rximagepicker.RxImageConverters;
import com.mlsdev.rximagepicker.RxImagePicker;
import com.mlsdev.rximagepicker.Sources;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static com.thefinestartist.utils.content.ContextUtil.getExternalFilesDir;

/**
 * Created by Arif Luthfiansyah on 22/09/2017.
 */

public class SignupProfileFragment extends Fragment {

    public static final String TAG = SignupProfileFragment.class.getSimpleName();
    private Context mContext;

    @BindView(R.id.iv_photo_user)
    CircleImageView mPhotoUserImageView;

    @BindView(R.id.form_layout)
    LinearLayout mFormLayout;

    @BindView(R.id.et_name)
    EditText mNameEditText;

    @BindView(R.id.et_handphone)
    EditText mHandphoneEditText;

    @BindView(R.id.et_address)
    EditText mAddressEditText;

    @BindView(R.id.progressBar)
    ProgressBar mProgressbar;

    private Profile mProfile = new Profile();
    private Fragment mFragment = new SignupContactFragment();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_profile, container, false);
        ButterKnife.bind(this, view);
        setupSubcribetRxImagePicker();
        return view;
    }

    private void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(mContext);
        startActivity(intent);
    }

    private void sendDataToFragment(Fragment fragment, String name,
                                    String handphone, String address, String photo) {
        Bundle bundle = new Bundle();
        bundle.putString("nameKey", name);
        bundle.putString("handphoneKey", handphone);
        bundle.putString("addressKey", address);
        bundle.putString("photoKey", photo);
        fragment.setArguments(bundle);
    }

    @OnClick(R.id.tv_btn_login)
    public void doLogin() {
        openLoginActivity();
    }

    @OnClick(R.id.iv_photo_user)
    public void doUploadUserPhoto() {
        pickImageFromSource(Sources.GALLERY);
    }

    @OnClick(R.id.btn_next_register)
    public void doNextStepRegister() {
        attemptRegister();
    }

    private void setupSubcribetRxImagePicker() {
        if (RxImagePicker.with(mContext).getActiveSubscription() != null) {
            RxImagePicker.with(mContext).getActiveSubscription()
                    .subscribe(new Consumer<Uri>() {
                        @Override
                        public void accept(@NonNull Uri uri) throws Exception {
                            onImagePicked(uri);
                        }
                    });
        }
    }

    private void pickImageFromSource(Sources sources) {
        RxImagePicker.with(mContext).requestImage(sources)
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
                        onImagePicked(f);
                        Log.d(TAG, f.getName());
                        mProfile.setPhoto(f.getName());
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
            Glide.with(mContext)
                    .load(result)
                    .into(mPhotoUserImageView);
        }
    }

    //TODO Tolong dirapihkan lagi di sini
    private void attemptRegister() {
        boolean cancel = false;
        View focusView = null;

        // Set all error view to null
        setErrorView(null);

        // Set handphone & email value
        mProfile.setName(mNameEditText.getText().toString());
        mProfile.setHandphone(mHandphoneEditText.getText().toString());
        mProfile.setAddress(mAddressEditText.getText().toString());

        // Check for a valid name.
        if (mProfile.getName().isEmpty()) {
            setErrorNameView(getString(R.string.error_field_required));
            focusView = mNameEditText;
            cancel = true;
        }

        // Check for a valid handphone number.
        if (mProfile.getHandphone().isEmpty()) {
            setErrorHandphoneView(getString(R.string.error_field_required));
            focusView = mHandphoneEditText;
            cancel = true;
        } else if (!isHandphoneValid()) {
            setErrorHandphoneView(getString(R.string.error_invalid_handphone));
            focusView = mHandphoneEditText;
            cancel = true;
        }

        // Check for a valid address.
        if (mProfile.getAddress().isEmpty()) {
            setErrorAddressView(getString(R.string.error_field_required));
            focusView = mAddressEditText;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            sendDataToFragment(
                    mFragment, mProfile.getName(), mProfile.getHandphone(),
                    mProfile.getAddress(), mProfile.getPhoto()
            );
            String className = mFragment.getClass().getSimpleName();
            SignupActivity.commitTransactionFragment(mFragment, className);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources()
                    .getInteger(android.R.integer.config_shortAnimTime);

            showFormLayout(show);
            mFormLayout.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    showFormLayout(show);
                    showContentIcon(show);
                }
            });

            showProgessbar(show);
            mProgressbar.animate()
                    .setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    showProgessbar(show);
                }
            });
        } else {
            showProgessbar(show);
            showFormLayout(show);
        }
    }

    //TODO Jangan lupa dibenerin validasinya
    private boolean isHandphoneValid() {
        return mProfile.getHandphone().length() > 1;
    }

    private void setErrorNameView(String message) {
        mNameEditText.setError(message);
    }

    private void setErrorHandphoneView(String message) {
        mHandphoneEditText.setError(message);
    }

    private void setErrorAddressView(String message) {
        mAddressEditText.setError(message);
    }

    private void setErrorView(String message) {
        mNameEditText.setError(message);
        mHandphoneEditText.setError(message);
        mAddressEditText.setError(message);
    }

    private void showProgessbar(boolean show) {
        mProgressbar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void showFormLayout(boolean show) {
        mFormLayout.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    private void showContentIcon(boolean show) {
        mPhotoUserImageView.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    private File createTempFile() {
        return new File(getExternalFilesDir(
                Environment.DIRECTORY_PICTURES),
                System.currentTimeMillis() + "_img_belajaryuk.jpeg");
    }

    private void showToastMessage(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }
}
