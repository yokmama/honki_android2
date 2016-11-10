package com.kayosystem.honki.chapter06.lesson28;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ToggleButtonのインスタンスを取得
        ToggleButton tb = (ToggleButton) findViewById(R.id.switchAutoWallpaper);

        // ToggleButtonに前回の値をセット
        final PreferenceDao pref = new PreferenceDao(this);
        tb.setChecked(pref.isAutoWallpaperEnabled());

        //リスナーをセット
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // 壁紙の変更を開始
                    startSearch();
                } else {
                    // 壁紙の変更を停止
                    stopSearch();
                }
                //壁紙変更の設定を保存
                pref.setAutoWallpaperEnabled(isChecked);
            }
        });
    }

    /**
     * 壁紙の変更を開始
     */
    private void startSearch() {
        // 検索キーワード固定
        //TODO:レッスンではここにプログラムを追加

        // バックグラウンドで検索と画像の取得を行う
        //TODO:レッスンではここにプログラムを追加
    }

    /**
     * 壁紙の変更を停止
     */
    private void stopSearch() {
        //TODO:レッスンではここにプログラムを追加
    }
}
