package com.apps911.brunotrovo.n26bc.data.bitcoin;

import android.support.annotation.Nullable;

class BitCoinPriceItemRaw {

    @Nullable
    private final Long x;

    @Nullable
    private final Double y;

    public BitCoinPriceItemRaw(@Nullable Long x,
                               @Nullable Double y) {
        this.x = x;
        this.y = y;
    }

    @Nullable
    Long getX() {
        return x;
    }

    @Nullable
    Double getY() {
        return y;
    }

}