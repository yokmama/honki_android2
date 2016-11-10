package com.kayosystem.honki.chapter03.lesson13;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, R.string.app_name, Toast. LENGTH_LONG).show();

                // ログを出力する
                Log.v("MyAppTag", "VERBOSE");
                Log.d("MyAppTag", "DEBUG");
                Log.i("MyAppTag", "INFO");
                Log.w("MyAppTag", "WARN");
                Log.e("MyAppTag", "ERROR");

                // わざとアプリをクラッシュさせる
                Button nullButton = null;
                while (true) nullButton.setText("");
            }
        });
    }
}
