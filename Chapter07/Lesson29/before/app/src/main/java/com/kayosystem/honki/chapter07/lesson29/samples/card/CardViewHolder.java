package com.kayosystem.honki.chapter07.lesson29.samples.card;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kayosystem.honki.chapter07.lesson29.R;

public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView userName;
    ImageView userIcon;
    ImageView image;
    // TODO:レッスンではここにプログラムを追加

    private CardViewHolderListener listener;

    public CardViewHolder(final View itemView) {
        super(itemView);
        userName = (TextView) itemView.findViewById(R.id.user_name);
        userIcon = (ImageView) itemView.findViewById(R.id.user_icon);
        image = (ImageView) itemView.findViewById(R.id.image);

        // TODO:レッスンではここにプログラムを追加
    }

    /**
     * Swipeしたカードの情報位置を初期化する
     */
    public void initCard() {
        // TODO:レッスンではここにプログラムを追加
    }

    public void setListener(CardViewHolderListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onCardClick(CardViewHolder.this);
        }
    }

    public interface CardViewHolderListener {
        /**
         * カードをタップした時に呼びます。
         */
        void onCardClick(RecyclerView.ViewHolder holder);

        /**
         * スワイプの状態が変わった時に呼びます。
         */
        void onDragStateChanged(RecyclerView.ViewHolder holder, int state);

        /**
         * カードを非表示にした時に呼びます。
         */
        void onCardDismiss(RecyclerView.ViewHolder holder);
    }
}
