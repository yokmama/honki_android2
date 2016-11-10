package com.kayosystem.honki.chapter07.lesson31.samples.grid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.kayosystem.honki.chapter07.lesson31.R;
import com.kayosystem.honki.chapter07.lesson31.holders.ImageViewHolder;
import com.kayosystem.honki.chapter07.lesson31.item.BaseItem;
import com.kayosystem.honki.chapter07.lesson31.samples.SampleDataGenerator;

import java.util.List;

public class RecyclerViewGridActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_grid);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        // グリッドの個数返却処理
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = mRecyclerView.getAdapter().getItemViewType(position);
                if (type == R.id.index_type) {
                    return ((GridLayoutManager) mRecyclerView.getLayoutManager()).getSpanCount();
                } else {
                    return 1;
                }
            }
        });

        // サンプルのデータを作成
        final List<BaseItem> items = new SampleDataGenerator(this).generateIndexList();

        // GridViewスタイルのAdapetrを設定
        mRecyclerView.setAdapter(new MyGridAdapter(items));
    }
}
