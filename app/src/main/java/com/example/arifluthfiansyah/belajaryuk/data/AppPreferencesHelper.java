package com.example.arifluthfiansyah.belajaryuk.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Arif Luthfiansyah on 17/09/2017.
 */

public class AppPreferencesHelper {

    private static final String PREF_NAME = "BELAJARYUK";
    private static final String PREF_KEY_IS_FIRST_TIME         = "PREF_KEY_IS_FIRST_TIME";
    private static final String PREF_KEY_USER_LEVEL            = "PREF_KEY_USER_LEVEL";
    private static final String PREF_KEY_IS_LOGGED_IN          = "PREF_KEY_USER_ARE_LOGGED_IN";
    private static final String PREF_KEY_USER_AUTHORIZATION    = "PREF_KEY_USER_AUTHORIZATION";
    private static final String PREF_KEY_USER_CITY             = "PREF_KEY_USER_CITY";
    private static final String PREF_KEY_USER_COURSE           = "PREF_KEY_USER_COURSE";
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

    public void setUserLevel(String level) {
        mPrefs.edit().putString(PREF_KEY_USER_LEVEL, level).apply();
    }

    public String getUserLevel() {
        return mPrefs.getString(PREF_KEY_USER_LEVEL, "user");
    }

    public void setUserAuthorization(String authorization) {
        mPrefs.edit().putString(PREF_KEY_USER_AUTHORIZATION, "Bearer " + authorization).apply();
    }

    public String getUserAuthorization() {
        return mPrefs.getString(PREF_KEY_USER_AUTHORIZATION, "");
    }

    public void setIsLoggedIn(boolean loggedIn) {
        mPrefs.edit().putBoolean(PREF_KEY_IS_LOGGED_IN, loggedIn).apply();
    }

    public boolean getIsLoggedIn() {
        return mPrefs.getBoolean(PREF_KEY_IS_LOGGED_IN, false);
    }

    public void setUserCity(String location) {
        mPrefs.edit().putString(PREF_KEY_USER_CITY, location).apply();
    }

    public String getUserCity() {
        return mPrefs.getString(PREF_KEY_USER_CITY, "");
    }

    public void setUserCourse(String course){
        mPrefs.edit().putString(PREF_KEY_USER_COURSE, course).apply();
    }

    public String getUserCourse(){
        return mPrefs.getString(PREF_KEY_USER_COURSE, "Matematika");
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
