package com.apps911.brunotrovo.n26bc.utils;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by brunotrovo on 17/12/17.
 */

public class TimeUtils {

    @NonNull
    public String formatDate(@NonNull final Date date, @NonNull final String formatPattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatPattern, Locale.ENGLISH);
        return sdf.format(date);
    }

    @NonNull
    public String formatDateFromFloat(@NonNull final float dateValue, @NonNull final String formatPattern) {
        Date date = new Date((long) dateValue);
        SimpleDateFormat sdf = new SimpleDateFormat(formatPattern, Locale.ENGLISH);
        return sdf.format(date);
    }

}
