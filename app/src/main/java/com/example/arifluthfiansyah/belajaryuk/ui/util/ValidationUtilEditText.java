package com.example.arifluthfiansyah.belajaryuk.ui.util;

import android.text.TextUtils;

/**
 * Created by Arif Luthfiansyah on 09/11/2017.
 */

public class ValidationUtilEditText {

    public static boolean isEmailEmpty(String email) {
        return TextUtils.isEmpty(email);
    }

    public static boolean isEmailValid(String email) {
        return email.contains("@");
    }

    public static boolean isPasswordEmpty(String password) {
        return TextUtils.isEmpty(password);
    }

    public static boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}
