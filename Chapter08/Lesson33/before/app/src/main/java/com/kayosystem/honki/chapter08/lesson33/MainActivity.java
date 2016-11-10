package com.kayosystem.honki.chapter08.lesson33;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String FILE_NAME = "Lesson33.txt";
    // TODO:レッスンではここにプログラムを追加

    /**
     * ファイルパス
     */
    private TextView mTextViewFilePath;

    /**
     * コンテンツ
     */
    private EditText mEditTextContents;
    private RadioGroup mRadioGroup;

    /**
     * 最後に選択したボタンのID
     */
    private int mLastSelectedButtonId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                mTextViewFilePath.setText(getFilePath(i));
                mEditTextContents.setText("");
            }
        });

        findViewById(R.id.btnRead).setOnClickListener(this);
        findViewById(R.id.btnWrite).setOnClickListener(this);

        mEditTextContents = (EditText) findViewById(R.id.editText);

        mTextViewFilePath = (TextView) findViewById(R.id.textView);
        mTextViewFilePath.setText(getFilesDir().getPath() + "/" + FILE_NAME);
    }

    /**
     * ファイルパスを返します。
     *
     * @param id ラジオボタンのID
     * @return ファイルパス
     */
    private String getFilePath(int id) {
        // TODO:レッスンではここにプログラムを追加
        return "";
    }

    /**
     * テキストファイルの読み書き
     */
    private void readOrWrite() {
        if (mLastSelectedButtonId == R.id.btnRead) {

            // テキストファイル読み込み
            if (readText()) {
                Toast.makeText(MainActivity.this, "読み込み成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "読み込み失敗", Toast.LENGTH_SHORT).show();
            }

        } else if (mLastSelectedButtonId == R.id.btnWrite) {
            // テキストファイル書き込み
            if (writeText()) {
                Toast.makeText(MainActivity.this, "書き込み成功", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(MainActivity.this, "書き込み失敗", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private FileInputStream getFileInputStream() throws FileNotFoundException {
        // TODO:レッスンではここにプログラムを追加
        return null;
    }

    /**
     * テキストファイルの読み込み
     */
    private boolean readText() {

        FileInputStream fis = null;
        try {
            fis = getFileInputStream();
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }

            mEditTextContents.setText(sb.toString());

            br.close();
            isr.close();
            fis.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    private FileOutputStream getFileOutputStream() throws FileNotFoundException {

        int id = mRadioGroup.getCheckedRadioButtonId();

        if (R.id.radioInternalAppData == id) {
            return openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
        } else {
            File file = new File(getFilePath(id));
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }
            return new FileOutputStream(file);
        }
    }

    /**
     * テキストファイルの書き込み
     */
    private boolean writeText() {

        FileOutputStream fos = null;
        try {
            fos = getFileOutputStream();
            fos.write(mEditTextContents.getText().toString().getBytes());
            fos.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // TODO:レッスンではここにプログラムを追加
    }

    @Override
    public void onClick(View view) {
        // TODO:レッスンではここにプログラムを追加
    }

}
