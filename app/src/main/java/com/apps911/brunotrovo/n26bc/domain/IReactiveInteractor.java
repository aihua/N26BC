package com.apps911.brunotrovo.n26bc.domain;

import android.support.annotation.NonNull;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface IReactiveInteractor {

    interface IRetrieveInteractor<Object> extends IReactiveInteractor {

        @NonNull
        Observable<Object> getBehaviourStream();

        @NonNull
        Completable fetch();

    }

}