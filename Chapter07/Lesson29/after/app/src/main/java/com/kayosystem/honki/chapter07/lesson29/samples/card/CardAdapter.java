package com.kayosystem.honki.chapter07.lesson29.samples.card;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kayosystem.honki.chapter07.lesson29.R;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> implements CardViewHolder.CardViewHolderListener {

    private final ArrayList<DogEntity> mList;

    public CardAdapter(ArrayList<DogEntity> dogEntities) {
        super();
        this.mList = dogEntities;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_card, parent, false);

        CardViewHolder holder = new CardViewHolder(view);
        holder.setListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        DogEntity item = mList.get(position);

        // 値を設定
        holder.initCard();
        holder.userName.setText(item.name);
        holder.image.setImageResource(item.imageResId);
    }

    public void removeItem(int position) {
        mList.remove(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onCardClick(RecyclerView.ViewHolder holder) {
    }

    @Override
    public void onDragStateChanged(RecyclerView.ViewHolder holder, int state) {
    }

    @Override
    public void onCardDismiss(RecyclerView.ViewHolder holder) {
    }

}
