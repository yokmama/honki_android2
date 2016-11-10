package com.kayosystem.honki.chapter09.lesson40;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_PERMISSION_READ_EXTERNAL_STORAGE = 0x01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView tvDescription = (TextView) findViewById(R.id.label_permissions);
        tvDescription.setText(Manifest.permission.READ_EXTERNAL_STORAGE + "\nのパーミッションを要求します。");

        //パーミッションを要求ボタンをクリック
        findViewById(R.id.get_permissions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //パーミッションを要求
                doReqPermissions();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQ_PERMISSION_READ_EXTERNAL_STORAGE) {
            if (verifyPermissions(grantResults)) {
                Toast.makeText(this, "パーミッション要求が許可されました", Toast.LENGTH_SHORT).show();
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    Toast.makeText(this, "パーミッション要求が拒否されました", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "パーミッション要求が完全に拒否されています", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /**
     * パーミッションを要求.
     */
    private void doReqPermissions() {
        //現在のパーミッション取得状況をチェック
        if (PermissionChecker.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            //初回時に拒否されたか確認
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //パーミッションを要求
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQ_PERMISSION_READ_EXTERNAL_STORAGE);
            } else {
                //要求を一度拒否して2度目の要求を実施する際にパーミッションが必要な根拠を説明
                new AlertDialog.Builder(this)
                        .setTitle("パーミッション要求について")
                        .setMessage("本アプリでは外部ストレージの読み取りアクセスを許可する必要があります。許可しないとアプリが正常に動作しない可能性があります。")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //パーミッションを要求
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                        REQ_PERMISSION_READ_EXTERNAL_STORAGE);
                            }
                        })
                        .create().show();
            }
        }
    }

    /**
     * パーミッションを検証.
     *
     * @param grantResults
     * @return
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            //パーミッションが不許可ならfalseを返す
            if (result == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }
}