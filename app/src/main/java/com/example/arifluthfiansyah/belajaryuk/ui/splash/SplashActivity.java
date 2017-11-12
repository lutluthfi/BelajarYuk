package com.example.arifluthfiansyah.belajaryuk.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.example.arifluthfiansyah.belajaryuk.BaseActivity;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.ui.login.LoginActivity;
import com.example.arifluthfiansyah.belajaryuk.ui.main.MainActivity;

/**
 * Created by Arif Luthfiansyah on 09/11/2017.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i;
                if(checkIsLoggedIn()){
                    i = new Intent(SplashActivity.this, MainActivity.class);
                } else {
                    i = new Intent(SplashActivity.this, LoginActivity.class);
                }

                startActivity(i);
                finish();
            }
        }, 800);
    }

}
