package com.kayosystem.honki.chapter04.lesson16;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kayosystem.honki.chapter04.lesson16.fragment.ButtonFragment;
import com.kayosystem.honki.chapter04.lesson16.fragment.CheckBoxFragment;
import com.kayosystem.honki.chapter04.lesson16.fragment.EditTextFragment;
import com.kayosystem.honki.chapter04.lesson16.fragment.ImageButtonFragment;
import com.kayosystem.honki.chapter04.lesson16.fragment.ImageViewFragment;
import com.kayosystem.honki.chapter04.lesson16.fragment.ProgressBarFragment;
import com.kayosystem.honki.chapter04.lesson16.fragment.RadioButtonFragment;
import com.kayosystem.honki.chapter04.lesson16.fragment.RatingBarFragment;
import com.kayosystem.honki.chapter04.lesson16.fragment.SeekBarFragment;
import com.kayosystem.honki.chapter04.lesson16.fragment.ToastFragment;
import com.kayosystem.honki.chapter04.lesson16.fragment.SpinnerFragment;
import com.kayosystem.honki.chapter04.lesson16.fragment.SwitchFragment;
import com.kayosystem.honki.chapter04.lesson16.fragment.TextViewFragment;
import com.kayosystem.honki.chapter04.lesson16.fragment.ToggleButtonFragment;
import com.kayosystem.honki.chapter04.lesson16.fragment.WebViewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * これは、アプリ起動時の画面のプログラムです。
 * サンプルは、demoパッケージ以下にある各種Fragmentを御覧ください。
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    /**
     * 画面のリスト
     */
    private static final List<Sample> list = new ArrayList<Sample>() {
        {
            add(new Sample(TextViewFragment.class, 0, R.string.sample_subtitle_textview));
            add(new Sample(EditTextFragment.class, 0, R.string.sample_subtitle_edittext));
            add(new Sample(ButtonFragment.class, 0, R.string.sample_subtitle_button));
            add(new Sample(RadioButtonFragment.class, 0, R.string.sample_subtitle_radio));
            add(new Sample(CheckBoxFragment.class, 0, R.string.sample_subtitle_checkbox));
            if (Build.VERSION.SDK_INT >= 14) {
                add(new Sample(SwitchFragment.class, 0, R.string.sample_subtitle_switch));
            }
            add(new Sample(ToggleButtonFragment.class, 0, R.string.sample_subtitle_togglebutton));
            add(new Sample(ImageButtonFragment.class, 0, R.string.sample_subtitle_imagebutton));
            add(new Sample(ImageViewFragment.class, 0, R.string.sample_subtitle_imageview));
            add(new Sample(ProgressBarFragment.class, 0, R.string.sample_subtitle_progressbar));
            add(new Sample(SeekBarFragment.class, 0, R.string.sample_subtitle_seekbar));
            add(new Sample(RatingBarFragment.class, 0, R.string.sample_subtitle_ratingbar));
            add(new Sample(SpinnerFragment.class, 0, R.string.sample_subtitle_spinner));
            add(new Sample(WebViewFragment.class, 0, R.string.sample_subtitle_webview));
            add(new Sample(ToastFragment.class, 0, R.string.sample_subtitle_toast));
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

        // タッチされた項目の画面を表示
        Intent intent = new Intent(this, ShowFragmentActivity.class);
        intent.putExtra(ShowFragmentActivity.EXTRA_FRAGMENT_CLASS, sample.clazz.getName());
        intent.putExtra(ShowFragmentActivity.EXTRA_TITLE, getDefaultTitle(sample.clazz));
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
                convertView = inflater.inflate(R.layout.activity_main_row, parent, false);
            }

            // ビューの参照を取得
            TextView titleView = (TextView) convertView.findViewById(android.R.id.text1);
            TextView subtitleView = (TextView) convertView.findViewById(android.R.id.text2);

            // 内容を設定
            if (sample.title > 0) {
                titleView.setText(context.getString(sample.title));
            } else {
                // もしクラス名が"xxFragment"であれば、Fragmentを除いた名前にする
                titleView.setText(getDefaultTitle(sample.clazz));
            }
            if (sample.subtitle > 0) {
                subtitleView.setText(context.getString(sample.subtitle));
            } else {
                subtitleView.setText("");
            }

            return convertView;
        }
    }

    private static String getDefaultTitle(Class<?> clazz) {
        String className = clazz.getSimpleName();
        int activityIndex = className.lastIndexOf("Fragment");
        if (activityIndex >= 0) {
            return className.substring(0, activityIndex);
        } else {
            return className;
        }
    }

    private static class Sample {
        Class<? extends Fragment> clazz;
        @StringRes
        int title;
        @StringRes
        int subtitle;

        public Sample(Class<? extends Fragment> clazz, int title, int subtitle) {
            this.clazz = clazz;
            this.title = title;
            this.subtitle = subtitle;
        }
    }

}
