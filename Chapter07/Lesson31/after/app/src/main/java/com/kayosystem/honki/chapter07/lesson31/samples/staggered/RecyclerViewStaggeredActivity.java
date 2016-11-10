package com.kayosystem.honki.chapter07.lesson31.samples.staggered;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.kayosystem.honki.chapter07.lesson31.R;
import com.kayosystem.honki.chapter07.lesson31.holders.ImageViewHolder;
import com.kayosystem.honki.chapter07.lesson31.item.BaseItem;
import com.kayosystem.honki.chapter07.lesson31.samples.SampleDataGenerator;

import java.util.List;

public class RecyclerViewStaggeredActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_staggered);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        // サンプルのデータを作成
        final List<BaseItem> items = new SampleDataGenerator(this).generateStaggeredList();

        //千鳥状スタイルのAdapeterを設定
        mRecyclerView.setAdapter(new MyStaggeredAdapter(items));
    }
}
