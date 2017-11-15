package com.example.arifluthfiansyah.belajaryuk.ui.pelajaran;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pelajaran;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pelajarans;
import com.example.arifluthfiansyah.belajaryuk.network.rest.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Arif Luthfiansyah on 13/11/2017.
 */

public class PelajaranFragmentDialog extends DialogFragment implements
        PelajaranAdapter.PelajaranAdapterListener {

    private static final String TAG = PelajaranFragmentDialog.class.getSimpleName();
    private Context mContext;
    private Unbinder mUnbinder;

    @BindView(R.id.rv_diskusiyuk_pelajaran) RecyclerView mPelajaranRecyclerView;
    @BindView(R.id.pb_diskusiyuk_pelajaran) ProgressBar mPelajaranProgressBar;

    private static PelajaranFragmentListener mListener;
    private PelajaranAdapter mPelajaranAdapter;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public static PelajaranFragmentDialog newInstance(PelajaranFragmentListener fragmentListener) {
        PelajaranFragmentDialog f = new PelajaranFragmentDialog();
        mListener = fragmentListener;
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MyThemeFragmentDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_pelajaran, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setupRecyclerView();
        doFetchingPelajaranData();
        return view;
    }

    private void setupRecyclerView() {
        GridLayoutManager mLayoutManager = new GridLayoutManager(mContext, 3);
        mPelajaranRecyclerView.setLayoutManager(mLayoutManager);
        mPelajaranRecyclerView.setHasFixedSize(true);
        mPelajaranAdapter = new PelajaranAdapter(this);
        mPelajaranRecyclerView.setAdapter(mPelajaranAdapter);
    }

    private void doFetchingPelajaranData() {
        mPelajaranProgressBar.setVisibility(View.VISIBLE);
        mCompositeDisposable.add(ApiClient.get(mContext)
                .getPelajaranApiCall()
                .onBackpressureDrop()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserverPertanyaans())
        );
    }

    private DisposableObserver<Pelajarans> getObserverPertanyaans() {
        return new DisposableObserver<Pelajarans>() {
            @Override
            public void onNext(@NonNull Pelajarans pelajarans) {
                mPelajaranAdapter.addPelajarans(pelajarans);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                showToastMessage(e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Compelete Fetching Pelajaran");
                mPelajaranProgressBar.setVisibility(View.GONE);
            }
        };
    }

    @Override
    public void onPelajaranItemClick(Pelajaran pelajaran) {
        mListener.onPelajaranItemClick(pelajaran);
    }

    private void showToastMessage(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
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
        mCompositeDisposable.clear();
        mPelajaranAdapter.clearPelajarans();
    }

    public interface PelajaranFragmentListener {
        void onPelajaranItemClick(Pelajaran pelajaran);
    }
}
