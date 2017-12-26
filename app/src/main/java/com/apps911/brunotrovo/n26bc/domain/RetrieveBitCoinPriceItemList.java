package com.apps911.brunotrovo.n26bc.domain;

import android.support.annotation.NonNull;

import com.apps911.brunotrovo.n26bc.data.bitcoin.BitCoinPriceItem;
import com.apps911.brunotrovo.n26bc.data.bitcoin.BitCoinRepository;
import com.apps911.brunotrovo.n26bc.domain.IReactiveInteractor.IRetrieveInteractor;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class RetrieveBitCoinPriceItemList implements IRetrieveInteractor<List<BitCoinPriceItem>> {

    @NonNull
    private final BitCoinRepository bitCoinRepository;

    public RetrieveBitCoinPriceItemList(@NonNull BitCoinRepository bitCoinRepository) {
        this.bitCoinRepository = bitCoinRepository;
    }

    @NonNull
    @Override
    public Observable<List<BitCoinPriceItem>> getBehaviourStream() {
        return bitCoinRepository.getAllBitCoinItemPrices().doOnSubscribe(d ->
                bitCoinRepository.fetchBitCoinItemPrices().subscribe(() -> {})
        );
    }

    @NonNull
    @Override
    public Completable fetch() {
        return bitCoinRepository.fetchBitCoinItemPrices();
    }

}