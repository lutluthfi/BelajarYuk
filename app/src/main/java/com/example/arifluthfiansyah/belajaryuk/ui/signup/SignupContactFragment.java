package com.example.arifluthfiansyah.belajaryuk.ui.signup;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.arifluthfiansyah.belajaryuk.ui.main.MainActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.thefinestartist.utils.content.ContextUtil.getExternalFilesDir;

/**
 * Created by Arif Luthfiansyah on 21/09/2017.
 */

public class SignupContactFragment extends Fragment {

    public static final String TAG = SignupContactFragment.class.getSimpleName();
    private Context mContext;

    @BindView(R.id.form_layout)
    LinearLayout mFormLayout;

    @BindView(R.id.iv_photo_user)
    CircleImageView mPhotoUserImageView;

    @BindView(R.id.et_email)
    EditText mEmailEditText;

    @BindView(R.id.et_password)
    EditText mPasswordEditText;

    @BindView(R.id.progressBar)
    ProgressBar mProgressbar;

    private Profile mProfile = new Profile();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_contact, container, false);
        ButterKnife.bind(this, view);
        receiveDataFromFragment();
        setupPhotoUser();
        return view;
    }

    private void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(mContext);
        startActivity(intent);
        getActivity().finish();
    }

    private void receiveDataFromFragment() {
        Bundle bundle = this.getArguments();
        String name = bundle.getString("nameKey");
        String handphone = bundle.getString("handphoneKey");
        String address = bundle.getString("addressKey");
        String photo = bundle.getString("photoKey");
        mProfile.setName(name);
        mProfile.setHandphone(handphone);
        mProfile.setAddress(address);
        mProfile.setPhoto(photo);
        showToastMessage(name + " & " + handphone);
    }

    @OnClick(R.id.btn_register)
    public void doRegister() {
        attemptRegister();
    }

    private void setupPhotoUser() {
        String photo = mProfile.getPhoto();
        if (photo != null && !photo.isEmpty()) {
            File file = new File(
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                            + File.separator + mProfile.getPhoto()
            );
            Glide.with(mContext)
                    .load(file)
                    .into(mPhotoUserImageView);
        }
    }

    private void attemptRegister() {
        boolean cancel = false;
        View focusView = null;

        // Set all error view to null
        setErrorView(null);

        // Set email & password value
        mProfile.setEmail(mEmailEditText.getText().toString());
        mProfile.setPassword(mPasswordEditText.getText().toString());

        // Check for a valid email address.
        if (mProfile.getEmail().isEmpty()) {
            setErrorEmailView(getString(R.string.error_field_required));
            focusView = mEmailEditText;
            cancel = true;
        } else if (!isEmailValid()) {
            setErrorEmailView(getString(R.string.error_invalid_email));
            focusView = mEmailEditText;
            cancel = true;
        }

        // Check for a valid password
        if (mProfile.getPassword().isEmpty()) {
            setErrorPasswordView(getString(R.string.error_field_required));
            focusView = mPasswordEditText;
            cancel = true;
        } else if (!isPasswordValid()) {
            setErrorPasswordView(getString(R.string.error_invalid_password));
            focusView = mPasswordEditText;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            openMainActivity();
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
    private boolean isEmailValid() {
        return mProfile.getEmail().contains("@");
    }

    private boolean isPasswordValid() {
        return mProfile.getPassword().length() > 1;
    }

    private void setErrorEmailView(String message) {
        mEmailEditText.setError(message);
    }

    private void setErrorPasswordView(String message) {
        mPasswordEditText.setError(message);
    }

    private void setErrorView(String message) {
        mEmailEditText.setError(message);
        mPasswordEditText.setError(message);
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

    private void showToastMessage(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }
}
