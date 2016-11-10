package com.kayosystem.honki.chapter07.lesson29.samples.snackbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kayosystem.honki.chapter07.lesson29.R;

public class SnackbarActivity extends AppCompatActivity implements View.OnClickListener {

    private CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);

        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.layout_coordinator);
        Button btnSimple = (Button) findViewById(R.id.btn_simple);
        Button btnMultipleLine = (Button) findViewById(R.id.btn_multiple_line);
        Button btnWithButton = (Button) findViewById(R.id.btn_with_button);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        btnSimple.setOnClickListener(this);
        btnMultipleLine.setOnClickListener(this);
        btnWithButton.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_simple) {
            Snackbar.make(mCoordinatorLayout, "text", Snackbar.LENGTH_SHORT).show();
        } else if (id == R.id.btn_multiple_line) {
            Snackbar.make(mCoordinatorLayout, R.string.snackbar_multiple_line_text, Snackbar.LENGTH_SHORT).show();
        } else if (id == R.id.btn_with_button) {
            final Snackbar snackbar = Snackbar.make(mCoordinatorLayout, "text", Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("action", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Snackbar上のボタンが押された時
                    snackbar.dismiss();
                }
            });
            snackbar.show();
        } else if (id == R.id.fab) {
            Snackbar.make(mCoordinatorLayout, "Launch from FloatingActionButton", Snackbar.LENGTH_SHORT).show();
        } else {
            throw new RuntimeException("Unexpected button ID.");
        }
    }
}
