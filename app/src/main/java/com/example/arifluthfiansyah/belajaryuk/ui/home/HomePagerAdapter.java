package com.example.arifluthfiansyah.belajaryuk.ui.home;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arifluthfiansyah.belajaryuk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arif Luthfiansyah on 14/10/2017.
 */

public class HomePagerAdapter extends PagerAdapter {

    private List<Integer> mImages = new ArrayList<>();

    public HomePagerAdapter() {
        // Blank constructor
    }

    public void addImage(List<Integer> mImages) {
        this.mImages.addAll(mImages);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Context context = container.getContext();
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_list_content_upcoming, container, false);
        // Set background of card feature upcoming
        int background = context.getResources().getColor(mImages.get(position));
        CardView featureCardView = (CardView) view.findViewById(R.id.cv_feature_upcoming);
        featureCardView.setCardBackgroundColor(background);

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
        return mImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
