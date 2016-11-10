package com.kayosystem.honki.chapter07.lesson29.samples.card;

import android.support.annotation.DrawableRes;

public class DogEntity {
    String name;
    @DrawableRes
    int imageResId;
    /** 既にスワイプ済かどうか */
    boolean isRemoved;

    public DogEntity(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }
}
