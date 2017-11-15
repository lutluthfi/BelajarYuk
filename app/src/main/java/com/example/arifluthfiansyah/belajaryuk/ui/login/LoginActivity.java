package com.example.arifluthfiansyah.belajaryuk.ui.login;

import android.content.Intent;
import android.graphics.Typeface;
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

import com.example.arifluthfiansyah.belajaryuk.BaseActivity;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Passport;
import com.example.arifluthfiansyah.belajaryuk.network.model.Token;
import com.example.arifluthfiansyah.belajaryuk.network.model.User;
import com.example.arifluthfiansyah.belajaryuk.network.rest.ApiClient;
import com.example.arifluthfiansyah.belajaryuk.ui.main.MainActivity;
import com.example.arifluthfiansyah.belajaryuk.ui.signup.SignupActivity;
import com.example.arifluthfiansyah.belajaryuk.ui.util.ValidationUtilEditText;
import com.onesignal.OneSignal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_content) RelativeLayout mLoginLayout;
    @BindView(R.id.pb_login) ProgressBar mProgressbar;
    @BindView(R.id.form_layout) LinearLayout mFormLayout;
    @BindView(R.id.et_email) EditText mEmailEditText;
    @BindView(R.id.et_password) EditText mPasswordEditText;
    @BindView(R.id.btn_login) Button mLoginButton;
    @BindView(R.id.tv_signup) TextView mSignupTextView;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setupView();
    }

    private void setupView() {
        int flag = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
        String signup = getResources().getString(R.string.prompt_signup_now);
        int color = getResources().getColor(R.color.colorDeepLightBlue);
        SpannableString signupText = new SpannableString(signup);
        signupText.setSpan(new ForegroundColorSpan(color), 18, signup.length(), flag);
        signupText.setSpan(new StyleSpan(Typeface.BOLD), 18, signup.length(), flag);
        mSignupTextView.setText(signupText);
    }

    @OnClick(R.id.tv_signup)
    public void openSignupActivity(View view) {
        startActivity(SignupActivity.getStartIntent(this));
        finish();
    }

    @OnClick(R.id.btn_login)
    public void doLogin(View view) {
        setErrorView(null);

        // store the datas
        String email = mEmailEditText.getText().toString();
        String pass = mPasswordEditText.getText().toString();

        if(ValidationUtilEditText.isEmailEmpty(email) && !ValidationUtilEditText.isEmailValid(email)){
            mEmailEditText.requestFocus();
            mEmailEditText.setText(getString(R.string.error_invalid_email));
        } else if(ValidationUtilEditText.isPasswordEmpty(pass) && !ValidationUtilEditText.isPasswordValid(pass)) {
            mPasswordEditText.requestFocus();
            mPasswordEditText.setText(getString(R.string.error_invalid_password));
        } else {
            showProgress(true);
            doFetchingTokenData(email, pass);
        }
    }

    private Passport getLoginPassport(String email, String pass) {
        String apiClientSecret = getResources().getString(R.string.api_client_secret);
        Passport passport = new Passport();
        passport.setClientId(1);
        passport.setClientSecret(apiClientSecret);
        passport.setUsername(email);
        passport.setPassword(pass);
        passport.setGrantType("password");
        passport.setTheNewProvider("user");
        return passport;
    }

    private void doFetchingTokenData(String email, String pass) {
        mCompositeDisposable.add(ApiClient.get(this)
                .getTokenApiCall(getLoginPassport(email, pass))
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
                setAuthorizationKey(token.getAccessToken());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                String message = getResources().getString(R.string.error_failed_login);
                showSnackbar(mLoginLayout, message);
                showProgress(false);
            }

            @Override
            public void onComplete() {
                String playerId = OneSignal.getPermissionSubscriptionState().getSubscriptionStatus().getUserId();
                doFetchingUserData(playerId);
            }
        };
    }

    //TODO Post playerId and get User datas. Latest changes here
    private void doFetchingUserData(String playerId) {
        mCompositeDisposable.add(ApiClient.get(this)
                .getUserApiCall(getAuthorizationKey(), playerId)
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
                showToastMessage("Selamat datang " + name);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("", e.getMessage());
            }

            @Override
            public void onComplete() {
                showProgress(false);
                setIsLoggedIn();
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        };
    }

    private void showProgress(boolean show) {
        mProgressbar.setVisibility(show ? View.VISIBLE : View.GONE);
        mFormLayout.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    private void setErrorView(String message) {
        mEmailEditText.setError(message);
        mPasswordEditText.setError(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}