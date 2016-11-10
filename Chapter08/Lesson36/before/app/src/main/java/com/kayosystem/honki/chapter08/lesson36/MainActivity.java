package com.kayosystem.honki.chapter08.lesson36;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTxtToday;
    private TextView mTxtHistory;

    /**
     * 現在年月日(yyyy-MM-dd)を取得する
     *
     * @return 現在年月日(yyyy-MM-dd)
     */
    private String getDate() {
        CharSequence charSequence = android.text.format.DateFormat.format("yyyy-MM-dd", Calendar.getInstance());
        return charSequence.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtToday = (TextView) findViewById(R.id.txToday);
        mTxtToday.setText(getDate());

        findViewById(R.id.btnArrive).setOnClickListener(this);
        findViewById(R.id.btnLeave).setOnClickListener(this);

        mTxtHistory = (TextView) findViewById(R.id.txtHistory);

        setup();
    }

    /**
     * Firebaseのデータベースからデータを取得する
     */
    private void setup() {
        // TODO:レッスンではここにプログラムを追加
    }

    /**
     * 出社時間の追加
     * 同日に２回呼び出した場合は上書きする
     *
     * @param date   年月日
     * @param arrive 出社時刻
     */
    private void add(String date, String arrive) {
        // TODO:レッスンではここにプログラムを追加
    }

    /**
     * 退社時間の更新
     *
     * @param date  年月日
     * @param leave 退社時刻
     */
    private void update(String date, String leave) {
        // TODO:レッスンではここにプログラムを追加
    }

    /**
     * 現在時刻(HH:mm)を取得する
     *
     * @return 現在時刻(HH:mm)
     */
    private String getTime() {
        CharSequence charSequence = android.text.format.DateFormat.format("HH:mm", Calendar.getInstance());
        return charSequence.toString();
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if (R.id.btnArrive == id) {

            add(getDate(), getTime());
        } else {
            update(getDate(), getTime());
        }

    }
}
