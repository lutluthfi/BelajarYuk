package com.example.arifluthfiansyah.belajaryuk.ui.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.controller.UserController;
import com.example.arifluthfiansyah.belajaryuk.data.AppPreferencesHelper;
import com.example.arifluthfiansyah.belajaryuk.network.model.Passport;
import com.example.arifluthfiansyah.belajaryuk.network.model.Token;
import com.example.arifluthfiansyah.belajaryuk.network.model.User;
import com.example.arifluthfiansyah.belajaryuk.network.rest.ApiClient;
import com.example.arifluthfiansyah.belajaryuk.ui.main.MainActivity;
import com.example.arifluthfiansyah.belajaryuk.ui.signup.SignupActivity;
import com.example.arifluthfiansyah.belajaryuk.ui.util.ValidationUtilEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private String mEmail, mPassword;

    @BindView(R.id.login_content)
    RelativeLayout mLoginContent;

    @BindView(R.id.pb_login)
    ProgressBar mProgressbar;

    @BindView(R.id.form_layout)
    LinearLayout mFormLayout;

    @BindView(R.id.et_email)
    EditText mEmailEditText;

    @BindView(R.id.et_password)
    EditText mPasswordEditText;

    @BindView(R.id.btn_login)
    Button mLoginButton;

    @BindView(R.id.tv_signup)
    TextView mSignupTextView;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setupSignupView();
    }

    private void setIsLoggedIn() {
        AppPreferencesHelper.with(this).setIsLoggedIn(true);
    }

    private void setupSignupView() {
        int flag = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
        String signup = getResources().getString(R.string.prompt_signup_now);
        int color = getResources().getColor(R.color.colorDeepLightBlue);
        SpannableString signupText = new SpannableString(signup);
        signupText.setSpan(new ForegroundColorSpan(color), 18, signup.length(), flag);
        signupText.setSpan(new StyleSpan(Typeface.BOLD), 18, signup.length(), flag);
        mSignupTextView.setText(signupText);
    }

    private void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.tv_signup)
    public void openSignupActivity(View view) {
        Intent intent = SignupActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_login)
    public void doLogin(View view) {
        attemptLogin();
    }

    private void attemptLogin() {
        setErrorView(null);
        boolean cancel = false;
        View focusView = null;

        // Store values at the time of the login attempt.
        setEmail(mEmailEditText.getText().toString());
        setPassword(mPasswordEditText.getText().toString());

        // Check for a valid password, if the user entered one.
        if (!ValidationUtilEditText.isPasswordEmpty(getPassword()) &&
                !ValidationUtilEditText.isPasswordValid(getPassword())) {
            setErrorPasswordView(getString(R.string.error_invalid_password));
            focusView = mPasswordEditText;
            cancel = true;
        }

        // Check for a valid email address.
        if (ValidationUtilEditText.isEmailEmpty(getEmail())) {
            setErrorEmailView(getString(R.string.error_field_required));
            focusView = mEmailEditText;
            cancel = true;
        } else if (!ValidationUtilEditText.isEmailValid(getEmail())) {
            setErrorEmailView(getString(R.string.error_invalid_email));
            focusView = mEmailEditText;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            doFetchingTokenData();
        }
    }

    private Passport getDataPassport() {
        String apiClientSecret = getResources().getString(R.string.api_client_secret);
        Passport passport = new Passport();
        passport.setClientId(1);
        passport.setClientSecret(apiClientSecret);
        passport.setUsername(getEmail());
        passport.setPassword(getPassword());
        passport.setGrantType("password");
        passport.setTheNewProvider("user");
        return passport;
    }

    private void doFetchingTokenData() {
        mCompositeDisposable.add(ApiClient.get(this)
                .getTokenApiCall(getDataPassport())
                .onBackpressureDrop()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserverToken())
        );
    }

    private DisposableObserver<Token> getObserverToken() {
        return new DisposableObserver<Token>() {
            @Override
            public void onNext(@NonNull Token token) {
                String accessToken = token.getAccessToken();
                setKeyUserAuthorization(accessToken);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                String message = getResources().getString(R.string.error_failed_login);
                showSnackbar(message);
                showProgress(false);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Complete Fetching Token");
                doFetchingUserData();
            }
        };
    }

    private void setKeyUserAuthorization(String accesToken) {
        AppPreferencesHelper.with(this).setKeyUserAuthorization(accesToken);
    }

    private String getKeyUserAuthorization() {
        return AppPreferencesHelper.with(this).getUserAuthorization();
    }

    private void setKeyUserCity(String userCity) {
        AppPreferencesHelper.with(this).setUserCity(userCity);
    }

    private void doFetchingUserData() {
        mCompositeDisposable.add(ApiClient.get(this)
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
                String name = user.getNama();
                String city = user.getKabupaten();
                setKeyUserCity(city);
                showToastMessage("Selamat datang " + name);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Complete Fetching User");
                showProgress(false);
                setIsLoggedIn();
                openMainActivity();
            }
        };
    }

    private void showProgress(boolean show) {
        showProgessbar(show);
        showFormLayout(show);
    }

    private String getEmail() {
        return this.mEmail = mEmailEditText.getText().toString();
    }

    private void setEmail(String email) {
        this.mEmail = email;
    }

    private String getPassword() {
        return this.mPassword = mPasswordEditText.getText().toString();
    }

    private void setPassword(String password) {
        this.mPassword = password;
    }

    private void setErrorView(String message) {
        mEmailEditText.setError(message);
        mPasswordEditText.setError(message);
    }

    private void setErrorPasswordView(String message) {
        mPasswordEditText.setError(message);
    }

    private void setErrorEmailView(String message) {
        mEmailEditText.setError(message);
    }

    private void showProgessbar(boolean show) {
        mProgressbar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void showFormLayout(boolean show) {
        mFormLayout.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    private void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showSnackbar(String message) {
        Snackbar.make(mLoginContent, message, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}