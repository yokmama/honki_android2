package com.kayosystem.honki.chapter08.lesson33;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
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
    private static final int REQUEST_RUNTIME_PERMISSION = 1;

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

        if (id == R.id.radioInternalAppData) {
            // 内部ストレージ アプリデータ領域
            return getFilesDir() + "/" + FILE_NAME;
        } else if (id == R.id.radioInternalAppCache) {
            // 内部ストレージ アプリキャッシュ領域
            return getCacheDir() + "/" + FILE_NAME;
        } else if (id == R.id.radioExternalAppData) {
            // 外部ストレージ アプリデータ領域
            return ContextCompat.getExternalFilesDirs(this, null)[0] + "/" + FILE_NAME;
        } else if (id == R.id.radioExternalAppCache) {
            // 外部ストレージ アプリキャッシュ領域
            return ContextCompat.getExternalCacheDirs(this)[0] + "/" + FILE_NAME;
        } else if (id == R.id.radioExternalShare) {
            // 外部ストレージ 共有領域
            return Environment.getExternalStoragePublicDirectory("Documents").getPath() + "/" + FILE_NAME;
        }

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

        int id = mRadioGroup.getCheckedRadioButtonId();

        if (R.id.radioInternalAppData == id) {
            return openFileInput(FILE_NAME);
        } else
            return new FileInputStream(getFilePath(id));
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

        switch (requestCode) {
            case REQUEST_RUNTIME_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    readOrWrite();
                } else {
                    Toast.makeText(MainActivity.this, "パーミッション権限がないため実行できませんでした", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    @Override
    public void onClick(View view) {

        mLastSelectedButtonId = view.getId();

        if (R.id.radioExternalShare == mRadioGroup.getCheckedRadioButtonId()) {

            if (PackageManager.PERMISSION_GRANTED != PermissionChecker.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // パーミッション許可ない場合はRuntimePermissionを要求して処理終了
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_RUNTIME_PERMISSION);
                return;
            }
        }

        // 読み書き実行
        readOrWrite();
    }

}
