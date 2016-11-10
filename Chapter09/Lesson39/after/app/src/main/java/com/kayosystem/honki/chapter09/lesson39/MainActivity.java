package com.kayosystem.honki.chapter09.lesson39;

import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String NOTIFY_GROUP_A = "key_notification_group_a";
    private static final String NOTIFY_GROUP_B = "key_notification_group_b";

    private static final int NOTIFY_ID1 = 0x001;
    private static final int NOTIFY_ID2 = 0x002;
    private static final int NOTIFY_ID3 = 0x003;
    private static final int NOTIFY_ID4 = 0x004;
    private static final int NOTIFY_ID5 = 0x005;
    private static final int NOTIFY_ID6 = 0x006;
    private static final int NOTIFY_ID7 = 0x007;

    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);

        String user1 = "太郎";
        createSummaryNotification(NOTIFY_ID1, NOTIFY_GROUP_A, user1 + "から2件のメッセージ");
        createNotification(NOTIFY_ID2, NOTIFY_GROUP_A, user1, "明日は現地集合で良いの？");
        createNotification(NOTIFY_ID3, NOTIFY_GROUP_A, user1, "BBQの材料って明日合流してから買いに行く？");

        String user2 = "花子";
        createSummaryNotification(NOTIFY_ID4, NOTIFY_GROUP_B, user2 + "から3件のメッセージ");
        createNotification(NOTIFY_ID5, NOTIFY_GROUP_B, user2, "今日帰って来る時牛乳買ってきて");
        createNotification(NOTIFY_ID6, NOTIFY_GROUP_B, user2, "あとポストも見てきて");
        createNotification(NOTIFY_ID7, NOTIFY_GROUP_B, user2, "晩御飯は冷蔵庫に入れてあるから");
    }


    /**
     * サマリー用通知を作成.
     *
     * @param notify_id
     * @param group_id
     * @param title
     */
    private void createSummaryNotification(int notify_id, String group_id, String title) {
        //通知設定を作成
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle(title);
        builder.setColor(ContextCompat.getColor(this, R.color.colorPrimary));
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setGroupSummary(true);
        builder.setStyle(new NotificationCompat.InboxStyle()
                .setSummaryText(title));
        builder.setGroup(group_id);

        //作成した通知設定で通知
        notificationManager.notify(notify_id, builder.build());
    }

    /**
     * 通知を作成.
     *
     * @param notify_id
     * @param group_id
     * @param title
     * @param message
     */
    private void createNotification(int notify_id, String group_id, String title, String message) {
        //通知設定を作成
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle(title);
        builder.setContentText(message);
        builder.setColor(ContextCompat.getColor(this, R.color.colorPrimary));
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setGroup(group_id);

        //作成した通知設定で通知
        notificationManager.notify(notify_id, builder.build());
    }
}
