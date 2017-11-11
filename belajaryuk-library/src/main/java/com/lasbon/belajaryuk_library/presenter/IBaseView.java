package com.lasbon.belajaryuk_library.presenter;

/**
 * Created by anthonyle on 9/5/17.
 */

public interface IBaseView {

    void showLoading();

    void hideLoading();

    void onError(Throwable e);

}
