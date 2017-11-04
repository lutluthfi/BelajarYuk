package com.example.arifluthfiansyah.belajaryuk.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.data.prefs.AppPreferencesHelper;
import com.example.arifluthfiansyah.belajaryuk.ui.login.LoginActivity;
import com.example.arifluthfiansyah.belajaryuk.ui.main.MainActivity;
import com.example.arifluthfiansyah.belajaryuk.ui.signup.SignupActivity;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Arif Luthfiansyah on 09/09/2017.
 */

public class SplashActivity extends AppCompatActivity implements
        ViewPager.OnPageChangeListener {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.indicator)
    CirclePageIndicator mPageIncdicator;

    private List<Integer> mLayouts;
    private SplashPagerAdapter mSliderAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        setupContentSlider();
        setupViewPagerSlider();
    }

    private void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }

    private void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }

    private void openRegisterActivity() {
        Intent intent = SignupActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }

    private void setupIsFirstTime(){
        if (AppPreferencesHelper.with(this).getIsFirstTime()) {
            openMainActivity();
        }
    }

    private void setupContentSlider() {
        mLayouts = new ArrayList<>();
        mLayouts.add(R.layout.content_slider_first);
        mLayouts.add(R.layout.content_slider_second);
        mLayouts.add(R.layout.content_slider_third);
    }

    private void setupViewPagerSlider() {
        mSliderAdapter = new SplashPagerAdapter();
        mSliderAdapter.addLayout(mLayouts);
        mViewPager.setAdapter(mSliderAdapter);
        mViewPager.setOnPageChangeListener(this);
        mPageIncdicator.setViewPager(mViewPager);
    }

    private int getViewPagerCurrentItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }

    @OnClick(R.id.btn_login)
    public void openLoginActivity(View view) {
        int current = getViewPagerCurrentItem(1);
        if (current < mLayouts.size()) {
            mViewPager.setCurrentItem(current);
            showLogDebug(Integer.toString(current));
        } else {
            showLogDebug("openLoginActivity");
            openLoginActivity();
        }
    }

    @OnClick(R.id.btn_register)
    public void openRegisterActivity(View view) {
        int current = getViewPagerCurrentItem(1);
        if (current < mLayouts.size()) {
            mViewPager.setCurrentItem(current);
            showLogDebug(Integer.toString(current));
        } else {
            showLogDebug("openRegisterActivity");
            openRegisterActivity();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void showLogDebug(String message) {
        Log.d(TAG, message);
    }
}
