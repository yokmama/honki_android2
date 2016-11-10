package com.kayosystem.honki.chapter09.lesson38;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //通知を作成
        NotiUtils.createNoti(MainActivity.this, null);
    }
}