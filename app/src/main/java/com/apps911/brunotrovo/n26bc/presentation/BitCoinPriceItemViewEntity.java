package com.apps911.brunotrovo.n26bc.presentation;

class BitCoinPriceItemViewEntity {

    private final float price;
    private final float date;

    BitCoinPriceItemViewEntity(float price, float date) {
        this.price = price;
        this.date = date;
    }

    float getPrice() {
        return price;
    }

    float getDate() {
        return date;
    }

}