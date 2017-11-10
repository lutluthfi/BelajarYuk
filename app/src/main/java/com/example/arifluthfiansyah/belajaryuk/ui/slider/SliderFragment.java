package com.example.arifluthfiansyah.belajaryuk.ui.slider;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.ui.signup.SignupActivity;
import com.example.arifluthfiansyah.belajaryuk.ui.signup.SignupProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Arif Luthfiansyah on 25/09/2017.
 */

public class SliderFragment extends Fragment {

    public static final String TAG = SliderFragment.class.getSimpleName();

    @BindView(R.id.imageView)
    ImageView mLogoImageView;

    private Fragment mFragment = new SignupProfileFragment();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_slider, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.imageView)
    public void doNext(View view) {
        String className = mFragment.getClass().getSimpleName();
        SignupActivity.commitTransactionFragment(mFragment, className);
    }
}
