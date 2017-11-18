package com.example.arifluthfiansyah.belajaryuk;

import android.app.Application;
import android.content.Context;

import com.example.arifluthfiansyah.belajaryuk.data.AppPreferencesHelper;
import com.example.arifluthfiansyah.belajaryuk.notification.NotificationOpenedHandler;
import com.example.arifluthfiansyah.belajaryuk.notification.NotificationReceivedHandler;
import com.onesignal.OneSignal;
import com.thefinestartist.Base;

import io.realm.Realm;

/**
 * Created by Arif Luthfiansyah on 09/09/2017.
 */

public class BelajaryukApp extends Application {

    private static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        Realm.init(this);
        Base.initialize(this);
        OneSignal.startInit(this)
                .setNotificationReceivedHandler(new NotificationReceivedHandler())
                .setNotificationOpenedHandler(new NotificationOpenedHandler())
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }

    public static Application getApplication(){
        return mApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onTerminate() {
        AppPreferencesHelper.with(this).clearAll();
        super.onTerminate();
    }
}
