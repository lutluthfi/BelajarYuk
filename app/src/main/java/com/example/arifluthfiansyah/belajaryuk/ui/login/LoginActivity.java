package com.example.arifluthfiansyah.belajaryuk.ui.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.ui.main.MainActivity;
import com.example.arifluthfiansyah.belajaryuk.ui.signup.SignupActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements
        TextView.OnEditorActionListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private String mEmail, mPassword;

    @BindView(R.id.pb_login)
    ProgressBar mProgressView;

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

    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setupListener();
        setupSignupView();
    }

    private void setupListener() {
        mPasswordEditText.setOnEditorActionListener(this);
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
        if (!isPasswordEmpty() && !isPasswordValid()) {
            setErrorPasswordView(getString(R.string.error_invalid_password));
            focusView = mPasswordEditText;
            cancel = true;
        }

        // Check for a valid email address.
        if (isEmailEmpty()) {
            setErrorEmailView(getString(R.string.error_field_required));
            focusView = mEmailEditText;
            cancel = true;
        } else if (!isEmailValid()) {
            setErrorEmailView(getString(R.string.error_invalid_email));
            focusView = mEmailEditText;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            openMainActivity();
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            showFormLayout(show);
            mFormLayout.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    showFormLayout(show);
                }
            });

            showProgessbar(show);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
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

    private String getEmail() {
        return mEmail = mEmailEditText.getText().toString();
    }

    private void setEmail(String email) {
        mEmail = email;
    }

    private boolean isEmailEmpty() {
        return TextUtils.isEmpty(getEmail());
    }


    private boolean isEmailValid() {
        return mEmail.contains("@");
    }

    private String getPassword() {
        return mPassword = mPasswordEditText.getText().toString();
    }

    private void setPassword(String password) {
        mPassword = password;
    }

    private boolean isPasswordEmpty() {
        return TextUtils.isEmpty(mPassword);
    }


    private boolean isPasswordValid() {
        return mPassword.length() > 4;
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
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void showFormLayout(boolean show) {
        mFormLayout.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == R.id.login || actionId == EditorInfo.IME_NULL) {
            attemptLogin();
            return true;
        }
        return false;
    }
}