package com.example.arifluthfiansyah.belajaryuk.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.arifluthfiansyah.belajaryuk.BaseActivity;
import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.data.AppPreferencesHelper;
import com.example.arifluthfiansyah.belajaryuk.ui.history.HistoryFragment;
import com.example.arifluthfiansyah.belajaryuk.ui.home.HomeFragment;
import com.example.arifluthfiansyah.belajaryuk.ui.activities.ActivitiesFragment;
import com.example.arifluthfiansyah.belajaryuk.ui.profile.ProfileFragment;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;

public class MainActivity extends BaseActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener, ProfileFragment.ProfileFragmentListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.content_layout) FrameLayout mContentLayout;
    @BindView(R.id.bottom_navigation) BottomNavigationView mBottomNavigationView;

    private Fragment mFragment;
    private FragmentManager mFragmentManager;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolbar();
        setupListener();
        setupBottomNavigation();
        setupFirstLoadContent();
    }

    private void setupListener() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setContentInsetStartWithNavigation(0);
        }
    }

    @SuppressLint("RestrictedApi")
    private void setupBottomNavigation() {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView)
                mBottomNavigationView.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e(TAG, "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Unable to change value of shift mode", e);
        }
    }

    private void setupFirstLoadContent() {
        mFragmentManager = getSupportFragmentManager();
        if (mFragment == null) {
            mFragment = new HomeFragment();
        }
        commitTransactionFragment(mFragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.navigation_home:
                mFragment = new HomeFragment();
                break;
            case R.id.navigation_history:
                mFragment = new HistoryFragment();
                break;
            case R.id.navigation_activies:
                mFragment = new ActivitiesFragment();
                break;
            case R.id.navigation_profile:
                mFragment = new ProfileFragment();
                break;
        }
        commitTransactionFragment(mFragment);
        return true;
    }

    private void commitTransactionFragment(Fragment fragment) {
        mFragmentManager.beginTransaction()
                .replace(R.id.content_layout, fragment)
                .commit();
    }

    @Override
    public void doLogout() {
        AppPreferencesHelper.with(this).clearAll();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFragment = mFragmentManager.findFragmentById(R.id.content_layout);
        mFragment.onActivityResult(requestCode, resultCode, data);
    }
}