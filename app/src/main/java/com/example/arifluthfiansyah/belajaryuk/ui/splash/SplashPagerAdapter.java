package com.example.arifluthfiansyah.belajaryuk.ui.splash;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arif Luthfiansyah on 18/08/2017.
 */

public class SplashPagerAdapter extends PagerAdapter {

    private List<Integer> mLayouts = new ArrayList<>();

    public SplashPagerAdapter() {
        // Blank constructor
    }

    public void addLayout(List<Integer> mLayouts) {
        this.mLayouts.addAll(mLayouts);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater)
                container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(mLayouts.get(position), container, false);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    @Override
    public int getCount() {
        return mLayouts.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
