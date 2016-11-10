package com.kayosystem.honki.chapter04.lesson17;

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

import com.kayosystem.honki.chapter04.lesson17.fragment.CoordinatorLayoutFragment;
import com.kayosystem.honki.chapter04.lesson17.fragment.FrameLayoutFragment;
import com.kayosystem.honki.chapter04.lesson17.fragment.GridLayoutFragment;
import com.kayosystem.honki.chapter04.lesson17.fragment.LinearLayoutFragment;
import com.kayosystem.honki.chapter04.lesson17.fragment.RelativeLayoutFragment;
import com.kayosystem.honki.chapter04.lesson17.fragment.RelativeLayout2Fragment;
import com.kayosystem.honki.chapter04.lesson17.fragment.SwipeRefreshLayoutFragment;
import com.kayosystem.honki.chapter04.lesson17.fragment.TabLayoutFragment;
import com.kayosystem.honki.chapter04.lesson17.fragment.TableLayoutFragment;

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
            add(new Sample(FrameLayoutFragment.class, 0, R.string.sample_subtitle_framelayout));
            add(new Sample(LinearLayoutFragment.class, 0, R.string.sample_subtitle_linearlayout));
            add(new Sample(TableLayoutFragment.class, 0, R.string.sample_subtitle_tablelayout));
            if (Build.VERSION.SDK_INT >= 14) {
                add(new Sample(GridLayoutFragment.class, 0, R.string.sample_subtitle_gridlayout));
            }
            add(new Sample(RelativeLayoutFragment.class, 0, R.string.sample_subtitle_relativelayout));
            add(new Sample(RelativeLayout2Fragment.class, 0, R.string.sample_subtitle_relativelayout2));
            add(new Sample(SwipeRefreshLayoutFragment.class, 0, R.string.sample_subtitle_swiperefreshlayout));
            add(new Sample(TabLayoutFragment.class, 0, R.string.sample_subtitle_tablayout));
            add(new Sample(CoordinatorLayoutFragment.class, 0, R.string.sample_subtitle_coordinatorlayout));
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
