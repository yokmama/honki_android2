package com.kayosystem.honki.chapter05.lesson24;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Buttonのリスナーを設定
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MyApplicationを取得しCountUpを実行
                MyApplication myApplication = (MyApplication) getApplication();
                myApplication.countUp();
            }
        });
    }
}
