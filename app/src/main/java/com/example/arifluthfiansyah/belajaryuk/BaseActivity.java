package com.example.arifluthfiansyah.belajaryuk;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.arifluthfiansyah.belajaryuk.data.AppPreferencesHelper;

/**
 * Created by rama on 12/11/17.
 */

public class BaseActivity extends AppCompatActivity {

    public boolean checkIsLoggedIn() {
        return AppPreferencesHelper.with(this).getIsLoggedIn();
    }

    public void setIsLoggedIn() {
        AppPreferencesHelper.with(this).setIsLoggedIn(true);
    }

    public void setAuthorizationKey(String token) {
        AppPreferencesHelper.with(this).setUserAuthorization(token);
    }

    public String getAuthorizationKey() {
        return AppPreferencesHelper.with(this).getUserAuthorization();
    }

    public void setUserLevelKey(String level) {
        AppPreferencesHelper.with(this).setUserLevel(String.valueOf(level));
    }

    public String getUserLevelKey() {
        return AppPreferencesHelper.with(this).getUserLevel();
    }

    public void setUserCityKey(String city) {
        AppPreferencesHelper.with(this).setUserCity(city);
    }

    public String getUserCityKey(){
        return AppPreferencesHelper.with(this).getUserCity();
    }

    public void showSnackbar(View rootView, String message) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void printLog(String tag, String message) {
        Log.d(tag, message);
    }
}
