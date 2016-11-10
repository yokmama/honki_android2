package com.kayosystem.honki.chapter07.lesson29.samples.card;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.kayosystem.honki.chapter07.lesson29.R;

import java.util.ArrayList;

public class CardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        // リストのサンプルデータを作成
        ArrayList<DogEntity> dogEntities = new ArrayList<>();
        dogEntities.add(new DogEntity("dog1", R.drawable.dog1));
        dogEntities.add(new DogEntity("dog2", R.drawable.dog2));
        dogEntities.add(new DogEntity("dog3", R.drawable.dog3));
        dogEntities.add(new DogEntity("dog4", R.drawable.dog4));
        dogEntities.add(new DogEntity("dog5", R.drawable.dog5));
        dogEntities.add(new DogEntity("dog6", R.drawable.dog6));
        dogEntities.add(new DogEntity("dog7", R.drawable.dog7));
        dogEntities.add(new DogEntity("dog8", R.drawable.dog8));
        dogEntities.add(new DogEntity("dog9", R.drawable.dog9));

        // リスト部分を初期化
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        // Adapterを設定
        recyclerView.setAdapter(new CardAdapter(dogEntities) {

            @Override
            public void onCardClick(RecyclerView.ViewHolder holder) {
                // アイテムクリック
                Toast.makeText(CardActivity.this, "onCardClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCardDismiss(RecyclerView.ViewHolder holder) {
                Toast.makeText(CardActivity.this, "onCardDismiss", Toast.LENGTH_SHORT).show();

                // アイテムを削除
                int position = recyclerView.getChildAdapterPosition(holder.itemView);
                this.notifyItemRemoved(position);
                this.removeItem(position);
            }

            @Override
            public void onDragStateChanged(RecyclerView.ViewHolder holder, int state) {
                Context context = CardActivity.this;
                if (state == SwipeDismissBehavior.STATE_DRAGGING) {
                    // ドラッグ開始時
                    Toast.makeText(context, "onDragStateChanged: STATE_DRAGGING", Toast.LENGTH_SHORT).show();
                } else if (state == SwipeDismissBehavior.STATE_IDLE) {
                    // ドラッグ終了時
                    Toast.makeText(context, "onDragStateChanged: STATE_IDLE", Toast.LENGTH_SHORT).show();
                } else if (state == SwipeDismissBehavior.STATE_SETTLING) {
                    // ドラッグ終了(指を離した)時、消えることが確定している場合
                    Toast.makeText(context, "onDragStateChanged: STATE_SETTLING", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
