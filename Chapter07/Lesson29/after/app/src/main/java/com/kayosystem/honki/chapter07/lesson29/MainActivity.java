package com.kayosystem.honki.chapter07.lesson29;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kayosystem.honki.chapter07.lesson29.samples.bottomsheet.BottomSheetActivity;
import com.kayosystem.honki.chapter07.lesson29.samples.card.CardActivity;
import com.kayosystem.honki.chapter07.lesson29.samples.navigationview.NavigationViewActivity;
import com.kayosystem.honki.chapter07.lesson29.samples.snackbar.SnackbarActivity;
import com.kayosystem.honki.chapter07.lesson29.samples.textinput.TextInputActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * これは、アプリ起動時の画面のプログラムです。
 * サンプルは、samplesパッケージ以下にある各種Activityを御覧ください。
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    /**
     * 画面のリスト
     */
    private static final List<Sample> list = new ArrayList<Sample>() {
        {
            add(new Sample(CardActivity.class, 0, R.string.sample_subtitle_card));
            add(new Sample(BottomSheetActivity.class, 0, R.string.sample_subtitle_bottom_sheet));
            add(new Sample(NavigationViewActivity.class, 0, R.string.sample_subtitle_navigation_view));
            add(new Sample(SnackbarActivity.class, 0, R.string.sample_subtitle_snackbar));
            add(new Sample(TextInputActivity.class, 0, R.string.sample_subtitle_text_input));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listView = new ListView(this);
        setContentView(listView);

        listView.setOnItemClickListener(this);
        listView.setAdapter(new MyAdapter());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Sample sample = list.get(position);
        Intent intent = new Intent(this, sample.clazz);
        startActivity(intent);
    }

    private static class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Sample getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Context context = parent.getContext();
            Sample sample = list.get(position);

            // ビューがまだなければ作る
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
            }

            // ビューの参照を取得
            TextView titleView = (TextView) convertView.findViewById(android.R.id.text1);
            TextView subtitleView = (TextView) convertView.findViewById(android.R.id.text2);

            // 内容を設定
            if (sample.title > 0) {
                titleView.setText(context.getString(sample.title));
            } else {
                // もしクラス名が"xxActivity"であれば、Activityを除いた名前にする
                String className = sample.clazz.getSimpleName();
                int activityIndex = className.lastIndexOf("Activity");
                if (activityIndex >= 0) {
                    titleView.setText(className.substring(0, activityIndex));
                } else {
                    titleView.setText(className);
                }
            }
            if (sample.subtitle > 0) {
                subtitleView.setText(context.getString(sample.subtitle));
            } else {
                subtitleView.setText("");
            }

            return convertView;
        }
    }

    private static class Sample {
        Class<? extends Activity> clazz;
        @StringRes
        int title;
        @StringRes
        int subtitle;

        public Sample(Class<? extends Activity> clazz, int title, int subtitle) {
            this.clazz = clazz;
            this.title = title;
            this.subtitle = subtitle;
        }
    }

}
