package com.kayosystem.honki.chapter07.lesson31.samples.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.kayosystem.honki.chapter07.lesson31.R;
import com.kayosystem.honki.chapter07.lesson31.holders.ImageViewHolder;
import com.kayosystem.honki.chapter07.lesson31.item.BaseItem;
import com.kayosystem.honki.chapter07.lesson31.samples.SampleDataGenerator;

import java.util.List;

public class RecyclerViewListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // サンプルのデータを作成
        final List<BaseItem> items = new SampleDataGenerator(this).generateIndexList();

        // ListViewスタイルのAdapterを設定
        mRecyclerView.setAdapter(new MyListAdapter(items) {
            @Override
            public void onClick(ImageViewHolder holder) {
                int position = mRecyclerView.getChildAdapterPosition(holder.itemView);
                BaseItem baseItem = items.get(position);
                Toast.makeText(getApplicationContext(), "onClick:item=" + baseItem.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onLongClick(ImageViewHolder holder) {
                int position = mRecyclerView.getChildAdapterPosition(holder.itemView);
                BaseItem baseItem = items.get(position);
                Toast.makeText(getApplicationContext(), "onLongClick:item=" + baseItem.getName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
