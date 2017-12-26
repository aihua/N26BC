package com.apps911.brunotrovo.n26bc.presentation;

import com.annimon.stream.Stream;
import com.apps911.brunotrovo.n26bc.data.bitcoin.BitCoinPriceItem;

import java.util.List;

import io.reactivex.functions.Function;

public class BitCoinPriceListItemViewEntityMapper implements Function<List<BitCoinPriceItem>,
        List<BitCoinPriceItemViewEntity>> {

    @Override
    public List<BitCoinPriceItemViewEntity> apply(List<BitCoinPriceItem> bitCoinPriceItems) throws Exception {
        return Stream.of(bitCoinPriceItems).map(this::mapItem).toList();
    }

    private BitCoinPriceItemViewEntity mapItem(BitCoinPriceItem item) {
        return new BitCoinPriceItemViewEntity(item.getValue().floatValue(),
                item.getDate().getTime());
    }

}