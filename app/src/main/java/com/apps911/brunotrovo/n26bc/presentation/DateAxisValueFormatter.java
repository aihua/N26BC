package com.apps911.brunotrovo.n26bc.presentation;

import android.support.annotation.NonNull;

import com.apps911.brunotrovo.n26bc.utils.TimeUtils;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by brunotrovo on 17/12/17.
 */

class DateAxisValueFormatter implements IAxisValueFormatter {

    @NonNull
    private final TimeUtils timeUtils;

    DateAxisValueFormatter(@NonNull TimeUtils timeUtils) {
        this.timeUtils = timeUtils;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return timeUtils.formatDateFromFloat(value, BitCoinPricePresentationConstants.DateFormats.CHART_DATE_FORMAT);
    }

}