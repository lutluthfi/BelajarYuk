package com.example.arifluthfiansyah.belajaryuk.ui.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.ui.slider.SliderFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arif Luthfiansyah on 20/09/2017.
 */

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = SignupActivity.class.getSimpleName();

    @BindView(R.id.content_register_layout)
    FrameLayout mContentLayout;

    @BindView(R.id.progressBar)
    ProgressBar mProgressbar;

    private Fragment mFragment;
    private static FragmentManager mFragmentManager;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SignupActivity.class);
    }

    public static void commitTransactionFragment(Fragment fragment, String tag) {
        mFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.content_register_layout, fragment, tag)
                .addToBackStack("TAG")
                .commit();
        Log.d(TAG, "Fragment TAG : " + fragment.getTag());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        setupFirstContentLayout();
    }

    private void setupFirstContentLayout() {
        mFragmentManager = getSupportFragmentManager();
        if (mFragment == null) {
            mFragment = new SliderFragment();
        }
        String className = mFragment.getClass().getSimpleName();
        commitTransactionFragment(mFragment, className);
    }

    @Override
    public void onBackPressed() {
        String className = mFragment.getClass().getSimpleName();
        if (mFragmentManager.findFragmentByTag(className).isVisible()) {
            super.onBackPressed();
            finish();
        } else {
            mFragmentManager.popBackStack();
        }
    }
}
