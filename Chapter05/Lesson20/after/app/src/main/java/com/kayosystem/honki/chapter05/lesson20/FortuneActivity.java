package com.kayosystem.honki.chapter05.lesson20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class FortuneActivity extends AppCompatActivity {

    private static final String[] FORTUNE_TABLE = {"大吉", "吉", "中吉", "小吉", "末吉", "凶"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortune);

        TextView textName = (TextView) findViewById(R.id.textName);
        TextView textFortune = (TextView) findViewById(R.id.textFortune);
        findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //パラメータを取得
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("user_name") && !TextUtils.isEmpty(intent.getStringExtra("user_name"))) {
            textName.setText(intent.getStringExtra("user_name") + "さんの今日の運勢は...");
        } else {
            textName.setText("今日の運勢は...");
        }

        Random random = new Random();
        int i = random.nextInt(6);
        textFortune.setText(FORTUNE_TABLE[i] + "です！");
    }
}
