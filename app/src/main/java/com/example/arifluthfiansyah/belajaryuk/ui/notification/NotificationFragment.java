package com.example.arifluthfiansyah.belajaryuk.ui.notification;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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

public class NotificationFragment extends Fragment {

    private static final String TAG = NotificationFragment.class.getSimpleName();
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        ButterKnife.bind(this, view);
        setupTitleFragment();
        return view;
    }

    private void setupTitleFragment() {
        String titleFragment = mContext.getResources()
                .getString(R.string.title_fragment_notification);
        getActivity().setTitle(titleFragment);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }
}
