package com.kayosystem.honki.chapter09.lesson38;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * 通知にメッセージを追加するbroadcastレシーバ.
 */
public class NotiBroadcastReceiver extends BroadcastReceiver {
    public static final String KEY_DIRECT_REPLY = "key_direct_reply";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent != null) {
            Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
            if (remoteInput != null) {
                //入力データを取得
                String value = (String) remoteInput.getCharSequence(KEY_DIRECT_REPLY);
                Toast.makeText(context, value, Toast.LENGTH_SHORT).show();

                //通知に反映して更新
                ArrayList<String> history = new ArrayList<>();
                history.add("コメント：" + value);
                NotiUtils.createNoti(context, history);
            }
        }
    }
}
