package com.example.arifluthfiansyah.belajaryuk.ui.belajaryuk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.arifluthfiansyah.belajaryuk.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Arif Luthfiansyah on 28/10/2017.
 */

public class BelajaryukCancelFragmentDialog extends DialogFragment implements
        View.OnClickListener {

    private static final String TAG = BelajaryukCancelFragmentDialog.class.getSimpleName();
    private Context mContext;
    private Unbinder mUnbinder;

    @BindView(R.id.btn_yes_cancel_belajaryuk)
    Button mYesCancelBelajaryukButton;

    @BindView(R.id.btn_no_cancel_belajaryuk)
    Button mNoCancelBelajaryukButton;

    private BelajaryukCancelListener mListener;

    public static BelajaryukCancelFragmentDialog newInstance(String content) {
        BelajaryukCancelFragmentDialog fragmentDialog = new BelajaryukCancelFragmentDialog();
        Bundle bundle = new Bundle();
        bundle.putString("keyContent", content);
        fragmentDialog.setArguments(bundle);
        return fragmentDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MyThemeFragmentDialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_belajaryuk_cancel, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setupListener();
        return view;
    }

    private void setupListener() {
        mYesCancelBelajaryukButton.setOnClickListener(this);
        mNoCancelBelajaryukButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_yes_cancel_belajaryuk) {
            mListener.onYesCancelButtonClick();
        } else {
            mListener.onNoCancelButtonClick();
        }
    }

    private void showToastMessage(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        mListener = (BelajaryukCancelListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    public interface BelajaryukCancelListener {
        void onYesCancelButtonClick();

        void onNoCancelButtonClick();
    }
}
