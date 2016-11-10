package com.kayosystem.honki.chapter07.lesson29.samples.card;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kayosystem.honki.chapter07.lesson29.R;

public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView userName;
    ImageView userIcon;
    ImageView image;
    CardView card;

    private CardViewHolderListener listener;

    public CardViewHolder(final View itemView) {
        super(itemView);
        userName = (TextView) itemView.findViewById(R.id.user_name);
        userIcon = (ImageView) itemView.findViewById(R.id.user_icon);
        image = (ImageView) itemView.findViewById(R.id.image);

        card = (CardView) itemView.findViewById(R.id.card);
        card.setOnClickListener(this);

        // ドラッグして消えるBehaviorを取り付ける。
        // スワイプする要素は、親ビューがCoordinatorLayoutである必要がある。
        SwipeDismissBehavior behavior = new SwipeDismissBehavior();
        behavior.setStartAlphaSwipeDistance(0.1f);
        behavior.setEndAlphaSwipeDistance(0.6f);
        behavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END);
        behavior.setListener(new SwipeDismissBehavior.OnDismissListener() {
            @Override
            public void onDismiss(View view) {
                if (listener != null) {
                    listener.onCardDismiss(CardViewHolder.this);
                }
            }

            @Override
            public void onDragStateChanged(int state) {
                if (listener != null) {
                    listener.onDragStateChanged(CardViewHolder.this, state);
                }
            }
        });
        final ViewGroup.LayoutParams cardViewLayoutParams = card.getLayoutParams();
        ((CoordinatorLayout.LayoutParams) cardViewLayoutParams).setBehavior(behavior);
    }

    /**
     * Swipeしたカードの情報位置を初期化する
     */
    public void initCard() {
        if (card != null) {
            card.setLeft(0);
            ViewCompat.setAlpha(card, 1f);
        }
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
