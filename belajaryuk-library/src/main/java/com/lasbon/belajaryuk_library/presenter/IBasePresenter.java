package com.lasbon.belajaryuk_library.presenter;

/**
 * Created by anthonyle on 9/5/17.
 */

public interface IBasePresenter<T extends IBaseView> {


    void onViewActive(T view);

    void onViewInActive();

}
