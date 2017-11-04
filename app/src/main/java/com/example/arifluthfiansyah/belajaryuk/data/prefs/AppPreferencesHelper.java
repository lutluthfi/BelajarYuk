package com.example.arifluthfiansyah.belajaryuk.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Arif Luthfiansyah on 17/09/2017.
 */

public class AppPreferencesHelper {

    //TODO Buat preference untuk sekali login saja
    //TODO Jangan lupa buat preference untuk token jg ya sob
    private static final String PREF_NAME = "BELAJARYUK";
    private static final String PREF_KEY_IS_FIRST_TIME = "PREF_KEY_IS_FIRST_TIME";
    private static final String PREF_KEY_PRELOAD_MAIN_ACTIVITY = "PREG_KEY_PRELOAD_MAIN_ACTIVITY";
    private static final String PREF_KEY_KEGIATANS_TOTAL_PAGES = "PREF_KEY_KEGIATANS_TOTAL_PAGES";
    private static final String PREF_KEY_KEGIATANS_TOTAL_ITEMS = "PREF_KEY_KEGIATANS_TOTAL_ITEMS";
    private static final String PREF_KEY_KEGIATANS_PAGE_NUMBER = "PREF_KEY_KEGIATANS_PAGE_NUMBER";

    private static AppPreferencesHelper mInstance;
    private final SharedPreferences mPrefs;

    public AppPreferencesHelper(Context context) {
        mPrefs = context.getApplicationContext()
                .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static AppPreferencesHelper with(Context context) {
        if (mInstance == null) {
            mInstance = new AppPreferencesHelper(context);
        }
        return mInstance;
    }

    public void clearAll() {
        mPrefs.edit().clear().apply();
    }

    public void setIsFirstTime(boolean firstTime) {
        mPrefs.edit().putBoolean(PREF_KEY_IS_FIRST_TIME, firstTime).apply();
    }

    public boolean getIsFirstTime() {
        return mPrefs.getBoolean(PREF_KEY_IS_FIRST_TIME, false);
    }

    public void setPreloadMainActivity(boolean preload) {
        mPrefs.edit().putBoolean(PREF_KEY_PRELOAD_MAIN_ACTIVITY, preload).apply();
    }

    public boolean getPreloadMainActivity() {
        return mPrefs.getBoolean(PREF_KEY_PRELOAD_MAIN_ACTIVITY, false);
    }

    public void setKegiatansTotalPages(int totalPages) {
        mPrefs.edit().putInt(PREF_KEY_KEGIATANS_TOTAL_PAGES, totalPages).apply();
    }

    public int getKegiatansTotalPages() {
        return mPrefs.getInt(PREF_KEY_KEGIATANS_TOTAL_PAGES, 0);
    }

    public void setKegiatansTotalItems(int totalItems) {
        mPrefs.edit().putInt(PREF_KEY_KEGIATANS_TOTAL_ITEMS, totalItems).apply();
    }

    public int getKegiatansTotalItems() {
        return mPrefs.getInt(PREF_KEY_KEGIATANS_TOTAL_ITEMS, 0);
    }

    public void setKegiatansPageNumber(int pageNumber) {
        mPrefs.edit().putInt(PREF_KEY_KEGIATANS_PAGE_NUMBER, pageNumber).apply();
    }

    public int getKegiatansPageNumber() {
        return mPrefs.getInt(PREF_KEY_KEGIATANS_PAGE_NUMBER, 1);
    }
}
