package com.example.arifluthfiansyah.belajaryuk.controller;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.arifluthfiansyah.belajaryuk.data.model.Profile;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Arif Luthfiansyah on 25/09/2017.
 */

//TODO controller bukan untuk profile

public class ProfileController {

    private final Realm realm;
    private static ProfileController instance;

    public ProfileController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static ProfileController get(Activity activity) {
        if (instance == null) {
            instance = new ProfileController(activity.getApplication());
        }
        return instance;
    }

    public static ProfileController get(Fragment fragment) {
        if (instance == null) {
            instance = new ProfileController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static ProfileController getInstance() {
        return instance;
    }

    public void refresh() {
        realm.refresh();
    }

    public void clear() {
        realm.beginTransaction();
        realm.delete(Profile.class);
        realm.commitTransaction();
    }
}
