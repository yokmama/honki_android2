package com.kayosystem.honki.chapter07.lesson32;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final Dog[] DOGS = new Dog[] {
            new Dog("わんこ写真1", R.drawable.dog1),
            new Dog("わんこ写真2", R.drawable.dog2),
            new Dog("わんこ写真3", R.drawable.dog3),
            new Dog("わんこ写真4", R.drawable.dog4),
            new Dog("わんこ写真5", R.drawable.dog5),
            new Dog("わんこ写真6", R.drawable.dog6),
            new Dog("わんこ写真7", R.drawable.dog7),
            new Dog("わんこ写真8", R.drawable.dog8),
            new Dog("わんこ写真9", R.drawable.dog9),
            new Dog("わんこ写真10", R.drawable.dog10),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO:レッスンではここにプログラムを追加

        // 一覧を生成
        makeList();
    }

    private void makeList() {
        LinearLayout contents = (LinearLayout) findViewById(R.id.contents);
        contents.removeAllViews();
        for (Dog dog : DOGS) {
            View itemView = getLayoutInflater().inflate(R.layout.row_item, contents, false);

            // ビューを呼び出し
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            TextView textView = (TextView) itemView.findViewById(R.id.textView);

            // 値を設定
            textView.setText(dog.name);
            imageView.setImageResource(dog.resId);

            // ビューに追加
            contents.addView(itemView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    private static class Dog {
        String name;
        @DrawableRes
        int resId;

        public Dog(String name, int resId) {
            this.name = name;
            this.resId = resId;
        }
    }
}
