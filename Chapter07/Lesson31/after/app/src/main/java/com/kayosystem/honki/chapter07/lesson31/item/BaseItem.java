package com.kayosystem.honki.chapter07.lesson31.item;

public abstract class BaseItem {
    private String mName;

    public abstract int getType();

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
