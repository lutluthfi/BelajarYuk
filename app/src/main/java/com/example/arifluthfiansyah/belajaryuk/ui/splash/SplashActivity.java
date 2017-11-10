package com.example.arifluthfiansyah.belajaryuk.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.data.AppPreferencesHelper;
import com.example.arifluthfiansyah.belajaryuk.ui.login.LoginActivity;
import com.example.arifluthfiansyah.belajaryuk.ui.main.MainActivity;
import com.example.arifluthfiansyah.belajaryuk.ui.slider.SliderActivity;

/**
 * Created by Arif Luthfiansyah on 09/11/2017.
 */

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();
    private final Integer splashTime = 800;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SplashActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setupSplashScreen();
    }

    private void openSliderActivity() {
        Intent intent = SliderActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }

    private void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }

    private void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }

    private void setupSplashScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkIsLoggedIn();
            }
        }, splashTime);
    }

    // Check if user are logged in, goes to main, if not goes to slider
    private void checkIsLoggedIn() {
        String message = "Is Logged In : " + AppPreferencesHelper.with(this).getIsLoggedIn();
        Log.d(TAG, message);
        if  (!AppPreferencesHelper.with(this).getIsLoggedIn()) {
            openSliderActivity();
        } else {
            openMainActivity();
        }
    }
}
