package com.kayosystem.honki.chapter06.lesson25;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.edit);

        // キーボードを表示
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //メニューを生成
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //入力されているテキストを取得
        //TODO:レッスンではここにプログラムを追加

        //連携処理を実施
        //TODO:レッスンではここにプログラムを追加

        return super.onOptionsItemSelected(item);
    }

    /**
     * キーワードが空(null、または空文字列)かどうかを確認.
     *
     * @param keyword キーワード
     * @return キーワードが空なら true
     */
    private boolean checkEmpty(String keyword) {
        if (TextUtils.isEmpty(keyword)) {
            Toast.makeText(this, getString(R.string.lb_please_input_keyword),
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
