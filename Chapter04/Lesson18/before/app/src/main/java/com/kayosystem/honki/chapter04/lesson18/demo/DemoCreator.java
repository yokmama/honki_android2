package com.kayosystem.honki.chapter04.lesson18.demo;


import android.support.v4.app.Fragment;

public class DemoCreator {

    public static Fragment create(DemoContent.DemoItem demoItem) {
        try {
            Class cls = Class.forName(demoItem.getFragmentName());
            return (Fragment) cls.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
