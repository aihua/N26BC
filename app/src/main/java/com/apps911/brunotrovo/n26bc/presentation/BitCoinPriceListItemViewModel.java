package com.apps911.brunotrovo.n26bc.presentation;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.apps911.brunotrovo.n26bc.domain.RetrieveBitCoinPriceItemList;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BitCoinPriceListItemViewModel extends ViewModel {

    private static final String TAG = BitCoinPriceListItemViewModel.class.getSimpleName();

    @NonNull
    private final RetrieveBitCoinPriceItemList retrieveBitCoinPriceItemList;

    @NonNull
    private final BitCoinPriceListItemViewEntityMapper bitCoinPriceListItemViewEntityMapper;

    @NonNull
    private final MutableLiveData<List<BitCoinPriceItemViewEntity>> bitCoinListLiveData = new MutableLiveData<>();

    @NonNull
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    BitCoinPriceListItemViewModel(@NonNull RetrieveBitCoinPriceItemList retrieveBitCoinPriceItemList,
                                  @NonNull BitCoinPriceListItemViewEntityMapper bitCoinPriceListItemViewEntityMapper) {
        this.retrieveBitCoinPriceItemList = retrieveBitCoinPriceItemList;
        this.bitCoinPriceListItemViewEntityMapper = bitCoinPriceListItemViewEntityMapper;
        compositeDisposable.add(bindToBitCoinList());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    @NonNull
    LiveData<List<BitCoinPriceItemViewEntity>> getBitCoinListLiveDate() {
        return bitCoinListLiveData;
    }

    @NonNull
    private Disposable bindToBitCoinList() {
        return retrieveBitCoinPriceItemList.getBehaviourStream()
                .observeOn(Schedulers.computation())
                .map(bitCoinPriceListItemViewEntityMapper)
                .subscribe(bitCoinListLiveData::postValue,
                        e -> Log.e(TAG, "Error updating bit coin price list live data"));
    }

    void requestDataUpdate() {
        retrieveBitCoinPriceItemList.fetch().subscribe(() ->
                        Log.d(TAG, "Bit coin price list updated"),
                e -> Log.d(TAG, "Error updating bit coin price list"));
    }

}