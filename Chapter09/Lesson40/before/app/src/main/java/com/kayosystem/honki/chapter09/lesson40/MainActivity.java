package com.kayosystem.honki.chapter09.lesson40;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //ここに追加
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvDescription = (TextView) findViewById(R.id.label_permissions);
        tvDescription.setText(Manifest.permission.READ_EXTERNAL_STORAGE +
                "\nのパーミッションを要求します。");

        //パーミッションを要求ボタンをクリック
        findViewById(R.id.get_permissions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //パーミッションを要求
                //ここに追加
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //ここに追加
    }

    //ここに追加
}