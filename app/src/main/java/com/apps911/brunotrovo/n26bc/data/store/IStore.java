package com.apps911.brunotrovo.n26bc.data.store;

import android.support.annotation.NonNull;

import java.util.List;

public interface IStore<Value> {

    void putAll(@NonNull final List<Value> valueList);

    void clear();

    @NonNull
    List<Value> getAll();

    interface TemporaryStore<Value> extends IStore<Value> {
    }

}