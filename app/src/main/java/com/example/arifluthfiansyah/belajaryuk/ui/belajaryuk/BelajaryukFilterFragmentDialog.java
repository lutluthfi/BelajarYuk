package com.example.arifluthfiansyah.belajaryuk.ui.belajaryuk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arifluthfiansyah.belajaryuk.R;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Arif Luthfiansyah on 27/10/2017.
 */

public class BelajaryukFilterFragmentDialog extends Fragment {

    private static final String TAG = BelajaryukFilterFragmentDialog.class.getSimpleName();

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_belajaryuk_filter, container, false);

        return view;
    }
}
