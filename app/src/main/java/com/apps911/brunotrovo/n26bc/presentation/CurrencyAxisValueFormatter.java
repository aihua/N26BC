package com.apps911.brunotrovo.n26bc.presentation;

import android.support.annotation.NonNull;

import com.apps911.brunotrovo.n26bc.utils.CurrencyUtils;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by brunotrovo on 17/12/17.
 */

class CurrencyAxisValueFormatter implements IAxisValueFormatter {

    @NonNull
    private final CurrencyUtils currencyUtils;

    CurrencyAxisValueFormatter(@NonNull CurrencyUtils currencyUtils) {
        this.currencyUtils = currencyUtils;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return currencyUtils.formatPrice(value);
    }

}