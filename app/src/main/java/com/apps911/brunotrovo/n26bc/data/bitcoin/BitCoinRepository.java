package com.apps911.brunotrovo.n26bc.data.bitcoin;

import android.support.annotation.NonNull;

import com.apps911.brunotrovo.n26bc.data.store.IReactiveStore;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class BitCoinRepository {

    private  static final String TIME_SPAN = "30days";

    @NonNull
    private final IReactiveStore<BitCoinPriceItem> store;

    @NonNull
    private final IBitCoinPriceService service;

    @NonNull
    private final BitCoinMapper bitCoinMapper;

    public BitCoinRepository(@NonNull final IReactiveStore<BitCoinPriceItem> store,
                             @NonNull final IBitCoinPriceService service,
                             @NonNull final BitCoinMapper bitCoinMapper) {

        this.store = store;
        this.service = service;
        this.bitCoinMapper = bitCoinMapper;
    }

    @NonNull
    public Observable<List<BitCoinPriceItem>> getAllBitCoinItemPrices() {
        return store.getAll();
    }

    @NonNull
    public Completable fetchBitCoinItemPrices() {
        return service.getBitCoinPriceInfo(TIME_SPAN)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(bitCoinMapper)
                .doOnSuccess(store::replaceAll)
                .toCompletable();
    }

}