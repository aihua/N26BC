package com.apps911.brunotrovo.n26bc.data.bitcoin;

import android.support.annotation.Nullable;

import java.util.List;

public class BitCoinPriceInfoRaw {

    @Nullable
    private final String status;

    @Nullable
    private final String name;

    @Nullable
    private final String unit;

    @Nullable
    private final String period;

    @Nullable
    private final String description;

    @Nullable
    private final List<BitCoinPriceItemRaw> values;

    public BitCoinPriceInfoRaw(@Nullable String status,
                               @Nullable String name,
                               @Nullable String unit,
                               @Nullable String period,
                               @Nullable String description,
                               @Nullable List<BitCoinPriceItemRaw> values) {
        this.status = status;
        this.name = name;
        this.unit = unit;
        this.period = period;
        this.description = description;
        this.values = values;
    }

    @Nullable
    List<BitCoinPriceItemRaw> getValues() {
        return values;
    }

}