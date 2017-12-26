package com.apps911.brunotrovo.n26bc.data.bitcoin;

import android.support.annotation.NonNull;

import java.util.Date;

public class BitCoinPriceItem {

    @NonNull
    private final Date date;

    @NonNull
    private final Double value;

    BitCoinPriceItem(@NonNull Date date, @NonNull Double value) {
        this.date = date;
        this.value = value;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    @NonNull
    public Double getValue() {
        return value;
    }

}