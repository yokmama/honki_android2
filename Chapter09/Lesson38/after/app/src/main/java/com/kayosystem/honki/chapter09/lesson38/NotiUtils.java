package com.kayosystem.honki.chapter09.lesson38;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

/**
 * 通知関連のユーティルクラス.
 */
public class NotiUtils {
    private static final int REQ_PI_MAIN = 1;
    private static final int NOTIFY_ID = 0x001;

    public static void createNoti(Context context, ArrayList<String> history) {

        //リモートインプットを生成
        RemoteInput remoteInput = new RemoteInput.Builder(NotiBroadcastReceiver.KEY_DIRECT_REPLY)
                .setLabel("ここに文字を入力").build();

        //MainActivityへIntentを発行するPendingIntentを生成
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                REQ_PI_MAIN, new Intent(context, NotiBroadcastReceiver.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

        //通知アクションにリモートインプットを追加
        NotificationCompat.Action directReply =
                new NotificationCompat.Action.Builder(-1, "返信", pendingIntent)
                        .addRemoteInput(remoteInput).build();

        //通知設定を作成
        NotificationCompat.Builder noti = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("すごい写真が撮れた")
                .setContentText("この犬どう思う？")
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(((BitmapDrawable) context.getDrawable(R.drawable.dog01)).getBitmap()));

        if (history != null && history.size() > 0) {
            //入力データをメッセージ履歴に追加
            CharSequence[] cs = history.toArray(new CharSequence[history.size()]);
            noti.setRemoteInputHistory(cs);
        }
        noti.addAction(directReply);

        //作成した通知設定で通知
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFY_ID, noti.build());
    }
}
