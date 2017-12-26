package com.apps911.brunotrovo.n26bc.data.store;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;

public interface IReactiveStore<Value> {

    void storeAll(@NonNull final List<Value> modelList);

    void replaceAll(@NonNull final List<Value> modelList);

    Observable<List<Value>> getAll();

}