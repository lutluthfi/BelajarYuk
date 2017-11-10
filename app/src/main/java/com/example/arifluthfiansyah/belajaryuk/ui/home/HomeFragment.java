package com.example.arifluthfiansyah.belajaryuk.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.ui.bacayuk.BacayukActivity;
import com.example.arifluthfiansyah.belajaryuk.ui.belajaryuk.BelajaryukActivity;
import com.example.arifluthfiansyah.belajaryuk.ui.donasiyuk.DonasiyukActivity;
import com.example.arifluthfiansyah.belajaryuk.ui.kesiniyuk.KesiniyukActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Arif Luthfiansyah on 20/09/2017.
 */

public class HomeFragment extends Fragment implements
        ViewPager.OnPageChangeListener {

    private static final String TAG = HomeFragment.class.getSimpleName();
    private final int timeSlide = 5000;
    private int currentImage = 0;
    private Context mContext;
    private Unbinder mUnbinder;
    private Runnable mRunnable;
    private Handler mHandler;

    @BindView(R.id.vp_upcoming)
    ViewPager mUpcomingViewPager;

    @BindView(R.id.cv_belajaryuk)
    CardView mBelajaryukCardView;

    @BindView(R.id.cv_diskusiyuk)
    CardView mDiskusiyukCardView;

    @BindView(R.id.cv_donasiyuk)
    CardView mDonasiyukCardView;

    @BindView(R.id.cv_bacayuk)
    CardView mBacayukCardView;

    @BindView(R.id.cv_kesiniyuk)
    CardView mKesiniyukCardView;

    private List<Integer> mImages;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setupTitleFragment();
        setupContentSlider();
        setupUpcomingViewPager();
        setupAutoSlideViewPager();
        return view;
    }

    private void setupTitleFragment() {
        String titleFragment = mContext.getResources()
                .getString(R.string.title_fragment_home);
        getActivity().setTitle(titleFragment);
    }

    private void setupContentSlider() {
        mImages = new ArrayList<>();
        mImages.add(R.color.colorSoftGreen);
        mImages.add(R.color.colorSoftTeal);
        mImages.add(R.color.colorSoftRed);
        mImages.add(R.color.colorSoftOrange);
        mImages.add(R.color.colorSoftYellow);
    }

    private void setupUpcomingViewPager() {
        HomePagerAdapter pagerAdapter = new HomePagerAdapter();
        pagerAdapter.addImage(mImages);
        mUpcomingViewPager.setPageMargin(32);
        mUpcomingViewPager.setAdapter(pagerAdapter);
        mUpcomingViewPager.setOnPageChangeListener(this);
    }

    private void setupAutoSlideViewPager() {
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                if (mImages.size() == currentImage) {
                    currentImage = 0;
                } else {
                    currentImage++;
                }
                mUpcomingViewPager.setCurrentItem(currentImage, true);
                mHandler.postDelayed(this, timeSlide);
            }
        };
    }

    @OnClick(R.id.cv_belajaryuk)
    public void openBelajaryukActivity(View view) {
        Intent intent = BelajaryukActivity.getStartIntent(mContext);
        startActivity(intent);
    }

    @OnClick(R.id.cv_diskusiyuk)
    public void openDiskusiyukActivity(View view) {
        showToastMessage("Diskusi yuk");
    }

    @OnClick(R.id.cv_donasiyuk)
    public void openDonasiyukActivity(View view) {
        Intent intent = DonasiyukActivity.getStartIntent(mContext);
        startActivity(intent);
    }

    @OnClick(R.id.cv_bacayuk)
    public void openBacayukActivity(View view) {
        Intent intent = BacayukActivity.getStartIntent(mContext);
        startActivity(intent);
    }

    @OnClick(R.id.cv_kesiniyuk)
    public void openKesiniyukActivity(View view) {
        Intent intent = KesiniyukActivity.getStartIntent(mContext);
        startActivity(intent);
    }

    @Override
    public void onPageScrolled(int position,
                               float positionOffset,
                               int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        currentImage = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    private void showToastMessage(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable, timeSlide);
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
