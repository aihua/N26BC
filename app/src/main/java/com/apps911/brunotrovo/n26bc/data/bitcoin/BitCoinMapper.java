package com.apps911.brunotrovo.n26bc.data.bitcoin;

import com.annimon.stream.Stream;

import java.util.Date;
import java.util.List;

import io.reactivex.functions.Function;

public class BitCoinMapper implements Function<BitCoinPriceInfoRaw, List<BitCoinPriceItem>> {

    private static final int MILLISECONDS = 1000;

    @Override
    public List<BitCoinPriceItem> apply(BitCoinPriceInfoRaw bitCoinPriceInfoRaw) {

        return Stream.of(bitCoinPriceInfoRaw.getValues())
                .map(valueRaw ->
                        new BitCoinPriceItem(new Date(valueRaw.getX() * MILLISECONDS),
                                valueRaw.getY())).toList();

    }

}

// TODO: HANDLE MAPPING EXCEPTIONS