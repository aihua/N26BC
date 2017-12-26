package com.apps911.brunotrovo.n26bc.utils;

import android.support.annotation.NonNull;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by brunotrovo on 17/12/17.
 */

public class CurrencyUtils {

    @NonNull
    public String formatPrice(final double amount) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(amount);
    }

}