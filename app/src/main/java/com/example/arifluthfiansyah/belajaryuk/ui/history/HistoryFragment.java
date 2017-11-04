package com.example.arifluthfiansyah.belajaryuk.ui.history;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.arifluthfiansyah.belajaryuk.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Arif Luthfiansyah on 20/09/2017.
 */

public class HistoryFragment extends Fragment {

    private static final String TAG = HistoryFragment.class.getSimpleName();
    private Context mContext;
    private Unbinder mUnbinder;

    @BindView(R.id.textView)
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setupTitleFragment();
        //TODO jangan lupa di benerin
        textView.setText(mContext.getResources().getString(R.string.title_fragment_history));
        return view;
    }

    private void setupTitleFragment() {
        String title = mContext.getResources()
                .getString(R.string.title_fragment_history);
        getActivity().setTitle(title);
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
