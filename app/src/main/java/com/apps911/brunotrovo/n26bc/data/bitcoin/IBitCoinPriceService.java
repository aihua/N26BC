package com.apps911.brunotrovo.n26bc.data.bitcoin;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IBitCoinPriceService {

    @GET("charts/market-price")
    Single<BitCoinPriceInfoRaw> getBitCoinPriceInfo(@Query("timespan") String timeSpan);

}