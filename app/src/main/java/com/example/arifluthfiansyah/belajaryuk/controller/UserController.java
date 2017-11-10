package com.example.arifluthfiansyah.belajaryuk.controller;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.arifluthfiansyah.belajaryuk.network.model.User;

import io.realm.Realm;

/**
 * Created by Arif Luthfiansyah on 25/09/2017.
 */

// Class controller for user who are logged in
public class UserController {

    private final Realm realm;
    private User user;
    private static UserController instance;

    public UserController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static UserController get(Activity activity) {
        if (instance == null) {
            instance = new UserController(activity.getApplication());
        }
        return instance;
    }

    public static UserController get(Fragment fragment) {
        if (instance == null) {
            instance = new UserController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public User getUser(){
        return user;
    }

    public static UserController getInstance(){
        return instance;
    }

    public Realm getRealm() {
        return realm;
    }

    public void refresh() {
        realm.refresh();
    }
}
//TODO masih belom bisa digunakan karena tabrakan sama RealmObject
