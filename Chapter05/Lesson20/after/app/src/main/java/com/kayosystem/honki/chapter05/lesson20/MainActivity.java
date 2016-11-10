package com.kayosystem.honki.chapter05.lesson20;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int CALL_RESULT_CODE = 100;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextViewのインスタンスを取得
        mTextView = (TextView) findViewById(R.id.textView);

        //リスナーをセット
        findViewById(R.id.buttonEdit).setOnClickListener(this);
        findViewById(R.id.buttonFortune).setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CALL_RESULT_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                //EditActivityから受け取ったテキストを表示
                String text = data.getStringExtra("text");
                mTextView.setText(text);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonEdit) {
            //EditActivityを呼び出すIntentを生成
            Intent intent = new Intent(this, EditActivity.class);
            //textというパラメータを設定
            intent.putExtra("text", mTextView.getText());
            //startActivityForResultで起動
            startActivityForResult(intent, CALL_RESULT_CODE);
        } else {
            //FortuneActivityを呼び出すIntentを生成
            Intent intent = new Intent(this, FortuneActivity.class);
            intent.putExtra("user_name", mTextView.getText());
            startActivity(intent);
        }
    }
}
