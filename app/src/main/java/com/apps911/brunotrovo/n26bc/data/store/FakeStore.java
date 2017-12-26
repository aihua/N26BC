package com.apps911.brunotrovo.n26bc.data.store;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.List;


public class FakeStore<Value> implements IStore.TemporaryStore<Value> {


    private List<Value> localList = Collections.emptyList();

    @Override
    public void putAll(@NonNull List<Value> values) {
        this.localList = values;
    }

    @Override
    public void clear() {
        this.localList = Collections.emptyList();
    }

    @NonNull
    @Override
    public List<Value> getAll() {
        return localList;
    }

}