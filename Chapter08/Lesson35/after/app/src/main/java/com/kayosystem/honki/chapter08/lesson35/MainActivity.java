package com.kayosystem.honki.chapter08.lesson35;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient mClient;
    private LinearLayout mColorsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mColorsLayout = (LinearLayout) findViewById(R.id.colorsLayout);
        mClient = new OkHttpClient();

        loadColor();
    }

    private void loadColor() {
        mColorsLayout.removeAllViews();

        // 接続先
        String url = "https://raw.githubusercontent.com/yokmama/honki_android/master/samples/colors.json";

        // リクエストを作成
        Request request = new Request.Builder().url(url).build();
        Call call = mClient.newCall(request);

        // リクエストを非同期実行
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();

                JSONObject json = null;
                try {
                    json = new JSONObject(body);
                    JSONArray colorsArray = json.getJSONArray("colorsArray");
                    for (int i = 0; i < colorsArray.length(); i++) {
                        JSONObject colorObject = colorsArray.getJSONObject(i);
                        addItem(colorObject.getString("colorName"), colorObject.getString("hexValue"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MainActivity.this, "処理失敗", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void addItem(String colorName, String hexValue) {
        final TextView item = (TextView) getLayoutInflater().inflate(R.layout.color_row, null, false);

        item.setText(colorName);
        item.setBackgroundColor(Color.parseColor(hexValue));

        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mColorsLayout.addView(item, params);
            }
        });
    }

}
