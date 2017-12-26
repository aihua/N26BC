package com.apps911.brunotrovo.n26bc.data.store;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class FakeReactiveStore<Value> implements IReactiveStore<Value> {

    @NonNull
    private final IStore.TemporaryStore<Value> cache;

    @NonNull
    private final Subject<List<Value>> allSubject;

    public FakeReactiveStore(@NonNull IStore.TemporaryStore<Value> cache) {
        this.allSubject = PublishSubject.<List<Value>>create().toSerialized();
        this.cache = cache;
    }

    @Override
    public void storeAll(@NonNull List<Value> modelList) {
        cache.putAll(modelList);
        allSubject.onNext(modelList);
    }

    @Override
    public void replaceAll(@NonNull List<Value> modelList) {
        cache.clear();
        storeAll(modelList);
    }

    @Override
    public Observable<List<Value>> getAll() {
        return Observable.defer(() -> allSubject.startWith(getAllValues()))
                .observeOn(Schedulers.computation());
    }

    @NonNull
    private List<Value> getAllValues() {
        return cache.getAll();
    }

}