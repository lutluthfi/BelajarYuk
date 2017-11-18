package com.example.arifluthfiansyah.belajaryuk.ui.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Arif Luthfiansyah on 15/11/2017.
 */

public class FormatUtilTextView {

    public static NumberFormat currencyID() {
        Locale localeID = new Locale("in", "ID");
        return NumberFormat.getCurrencyInstance(localeID);
    }
}
