package com.example.arifluthfiansyah.belajaryuk.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arifluthfiansyah.belajaryuk.R;

import butterknife.ButterKnife;

/**
 * Created by Arif Luthfiansyah on 20/09/2017.
 */

public class ActivitiesFragment extends Fragment {

    private static final String TAG = ActivitiesFragment.class.getSimpleName();
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        ButterKnife.bind(this, view);
        setupTitleFragment();
        setHasOptionsMenu(true);
        return view;
    }

    private void setupTitleFragment() {
        String titleFragment = mContext.getResources()
                .getString(R.string.title_fragment_notification);
        if (getActivity() != null) {
            getActivity().setTitle(titleFragment);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }
}
