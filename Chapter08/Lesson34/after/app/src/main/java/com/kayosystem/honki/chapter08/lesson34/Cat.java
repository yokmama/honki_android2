package com.kayosystem.honki.chapter08.lesson34;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Cat extends RealmObject {

    @PrimaryKey
    public long id;
    public String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
