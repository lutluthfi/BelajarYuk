package com.example.arifluthfiansyah.belajaryuk.ui.profile;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arifluthfiansyah.belajaryuk.R;
import com.example.arifluthfiansyah.belajaryuk.network.model.Passport;
import com.example.arifluthfiansyah.belajaryuk.network.model.Token;
import com.example.arifluthfiansyah.belajaryuk.network.rest.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Arif Luthfiansyah on 20/09/2017.
 */

//TODO Tampilannya di sesuaikan lagi dengan yang sesungguhnya, pake nested scroll view aje ude
public class ProfileFragment extends Fragment {

    //TODO tampilannya ngikutin ruang guru aje atau youtube
    private static final String TAG = ProfileFragment.class.getSimpleName();
    private Context mContext;
    private Unbinder mUnbinder;

    @BindView(R.id.imageView)
    ImageView imageView;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setupTitleFragment();
        return view;
    }

    private void setupTitleFragment() {
        String titleFragment = mContext.getResources()
                .getString(R.string.title_fragment_profile);
        getActivity().setTitle(titleFragment);
    }

    private Passport getDataPassport() {
        Passport passport = new Passport();
        passport.setClientId(2);
        passport.setClientSecret("61qQ9jtKjd0i6oISdtr0YBhqXktmKsVC94lOSv2h");
        passport.setUsername("vandervort.lura@example.net");
        passport.setPassword("secret");
        passport.setGrantType("password");
        return passport;
    }

    private void doFetchingTokenData() {
        mCompositeDisposable.add(ApiClient.get(mContext)
                .getTokenApiCall(getDataPassport())
                .onBackpressureDrop()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserverToken())
        );
    }

    private DisposableObserver<Token> getObserverToken() {
        return new DisposableObserver<Token>() {
            @Override
            public void onNext(@NonNull Token token) {
            }

            @Override
            public void onError(@NonNull Throwable e) {
                showToasMessage(e.getMessage());
            }

            @Override
            public void onComplete() {
                showToasMessage("Complete get token");
            }
        };
    }

    private void showToasMessage(String message) {
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
    }
}
