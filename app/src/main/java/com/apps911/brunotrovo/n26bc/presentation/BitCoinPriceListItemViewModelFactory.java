package com.apps911.brunotrovo.n26bc.presentation;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.apps911.brunotrovo.n26bc.domain.RetrieveBitCoinPriceItemList;

/**
 * Created by brunotrovo on 17/12/17.
 */

public class BitCoinPriceListItemViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    private final RetrieveBitCoinPriceItemList retrieveBitCoinPriceItemList;

    @NonNull
    private final BitCoinPriceListItemViewEntityMapper bitCoinPriceListItemViewEntityMapper;

    BitCoinPriceListItemViewModelFactory(@NonNull RetrieveBitCoinPriceItemList retrieveBitCoinPriceItemList,
                                                @NonNull BitCoinPriceListItemViewEntityMapper bitCoinPriceListItemViewEntityMapper) {
        this.retrieveBitCoinPriceItemList = retrieveBitCoinPriceItemList;
        this.bitCoinPriceListItemViewEntityMapper = bitCoinPriceListItemViewEntityMapper;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BitCoinPriceListItemViewModel.class)) {
            return (T) new BitCoinPriceListItemViewModel(retrieveBitCoinPriceItemList, bitCoinPriceListItemViewEntityMapper);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
