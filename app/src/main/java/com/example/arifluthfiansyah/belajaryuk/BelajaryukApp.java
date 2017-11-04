package com.example.arifluthfiansyah.belajaryuk;

import android.app.Application;
import android.util.Log;

import com.thefinestartist.Base;

import io.realm.Realm;

/**
 * Created by Arif Luthfiansyah on 09/09/2017.
 */

public class BelajaryukApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Base.initialize(this);
    }
}
