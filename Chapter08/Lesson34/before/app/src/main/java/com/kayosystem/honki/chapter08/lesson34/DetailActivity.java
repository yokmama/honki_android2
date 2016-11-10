package com.kayosystem.honki.chapter08.lesson34;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    // TODO:レッスンではここにプログラムを追加
    private EditText mEditName;
    private long mCat_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // ソフトウェアキーボードを開く
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        mEditName = (EditText) findViewById(R.id.editText);
        findViewById(R.id.btnOK).setOnClickListener(this);
        findViewById(R.id.btnCancel).setOnClickListener(this);

        // 初期データの設定
        initData();

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.btnOK) {

            insert();
        }
        finish();
    }
    
    /**
     * 主キーを生成します。
     *
     * @return 次のID
     */
    public long nextCatId() {
        // TODO:レッスンではここにプログラムを追加
        return 0;
    }
    /**
     * 初期データの設定
     */
    private void initData(){
        // TODO:レッスンではここにプログラムを追加
    }

    /**
     * Catを追加します
     */
    public void insert() {
        // TODO:レッスンではここにプログラムを追加
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // TODO:レッスンではここにプログラムを追加
    }
}
