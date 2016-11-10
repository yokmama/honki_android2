package com.kayosystem.honki.chapter05.lesson20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;


public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // ソフトウェアキーボードを開く
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        //EditTextのインスタンスを取得
        mEditText = (EditText) findViewById(R.id.editText);

        //パラメータを取得
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("text")) {
            mEditText.setText(intent.getStringExtra("text"));
        }
        mEditText.selectAll();

        //リスナーをセット
        findViewById(R.id.buttonUpdate).setOnClickListener(this);
        findViewById(R.id.buttonCanel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        // 更新ボタンを押した時の処理
        if (R.id.buttonUpdate == v.getId()) {

            //EditTextに入力されているテキストをMainActivityに渡す
            Intent data = new Intent();
            data.putExtra("text", mEditText.getText().toString());
            setResult(RESULT_OK, data);
        }

        // 画面を終了
        finish();

    }
}
