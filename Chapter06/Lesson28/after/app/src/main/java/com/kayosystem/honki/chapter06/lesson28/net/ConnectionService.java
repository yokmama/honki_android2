package com.kayosystem.honki.chapter06.lesson28.net;

import android.app.IntentService;
import android.app.WallpaperManager;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.kayosystem.honki.chapter06.lesson28.WallpaperBroadcastReceiver;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

/**
 * バックグラウンドで通信を行うサービス
 */
public class ConnectionService extends IntentService {
    private static final String TAG = ConnectionService.class.getSimpleName();

    public static final String ACTION_START = "extra.START";
    public static final String ACTION_STOP = "extra.STOP";
    public static final String EXTRA_SEARCH_KEYWORD = "extra.SEARCH_KEYWORD";
    public static final String EXTRA_IMAGE_URL = "extra.IMAGE_URL";
    private RequestGoogleCustomSearchApi mApi;
    private RequestDownloadImage mRequestDownloadImage;

    public ConnectionService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Google APIにアクセスするためのクラス呼び出し
        mApi = new RequestGoogleCustomSearchApi(this);
        // 画像ダウンロードのためのクラス呼び出し
        mRequestDownloadImage = new RequestDownloadImage(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null) {
            Log.i(TAG, "onHandleIntent is null.");
            return;
        }

        if (ACTION_START.equals(intent.getAction())) {
            //壁紙の変更を開始
            Log.v(TAG, "キーワード検索");
            String keyword = intent.getStringExtra(EXTRA_SEARCH_KEYWORD);
            if (!TextUtils.isEmpty(keyword)) {
                startSearch(keyword);
            }
            Log.v(TAG, "壁紙の変更開始");
            WallpaperBroadcastReceiver.startPolling(getApplicationContext());
        } else if (ACTION_STOP.equals(intent.getAction())) {
            //壁紙の変更を停止
            Log.v(TAG, "壁紙の変更を停止");
            WallpaperBroadcastReceiver.cancelPolling(getApplicationContext());
            WallpaperManager wm = WallpaperManager.getInstance(getApplicationContext());
            try {
                wm.clear();
            } catch (IOException e) {
                Log.e(TAG, "壁紙のクリアに失敗しました。", e);
            }
        } else {
            // 画像のダウンロード
            String url = intent.getStringExtra(EXTRA_IMAGE_URL);
            if (!TextUtils.isEmpty(url)) {
                startDownloadImage(url);
            }
        }
    }

    /**
     * 壁紙の検索を開始.
     * @param keyword
     */
    private void startSearch(String keyword) {
        Log.v(TAG, "Start search: keyword=" + keyword);
        final List<CustomSearchApiItem> items;
        try {
            items = mApi.reqCustomSearchApiSync(keyword);
        } catch (MalformedURLException e) {
            Log.e(TAG, "URLの形式が不正です。", e);
            return;
        } catch (JSONException e) {
            Log.e(TAG, "JSONのパースに失敗", e);
            return;
        } catch (IOException e) {
            Log.e(TAG, "通信エラー", e);
            return;
        }

        // 既存の画像(別のキーワードで検索した時の一覧)を削除
        File[] files = mRequestDownloadImage.getImageDir().listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }

        // ダウンロードする画像一覧をキューに詰める
        if (items.size() > 0) {
            for (CustomSearchApiItem item : items) {
                String link = item.getLink();
                Intent intent = new Intent(this, getClass());
                intent.putExtra(EXTRA_IMAGE_URL, link);
                this.startService(intent);
            }
        }
    }

    /**
     * 画像のダウンロードを開始.
     * @param url URL
     */
    private void startDownloadImage(String url) {
        Log.v(TAG, "ダウンロード開始: url=" + url);

        try {
            // 画像を取得
            File file = mRequestDownloadImage.reqDownloadImageSync(url);

            // 元画像はディスプレイに合っていない場合があるので、
            // 任意でリサイズ処理を挟む。
            Log.v(TAG, "ダウンロード終了: file=" + file);
        } catch (IOException e) {
            Log.i(TAG, "ダウンロード失敗", e);
        }
    }
}
