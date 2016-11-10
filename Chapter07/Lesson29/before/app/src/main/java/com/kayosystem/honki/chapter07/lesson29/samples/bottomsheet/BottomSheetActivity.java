package com.kayosystem.honki.chapter07.lesson29.samples.bottomsheet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kayosystem.honki.chapter07.lesson29.R;

public class BottomSheetActivity extends AppCompatActivity implements View.OnClickListener {

    // TODO:レッスンではここにプログラムを追加

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);

        findViewById(R.id.btn_behavior).setOnClickListener(this);
        findViewById(R.id.btn_dialog).setOnClickListener(this);

        // TODO:レッスンではここにプログラムを追加
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_behavior) {
            // TODO:レッスンではここにプログラムを追加
        } else {
            BottomSheetDialogFragment fragment = new MyBottomSheetDialogFragment();
            fragment.show(getSupportFragmentManager(), "MyBottomSheetDialogFragment");
        }
    }

}
