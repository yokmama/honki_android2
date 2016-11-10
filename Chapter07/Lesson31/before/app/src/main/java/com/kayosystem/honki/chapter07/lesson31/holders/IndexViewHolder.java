package com.kayosystem.honki.chapter07.lesson31.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kayosystem.honki.chapter07.lesson31.R;

public class IndexViewHolder extends RecyclerView.ViewHolder {
    private TextView mTextView;

    public IndexViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.textView);
    }

    public TextView getTextView() {
        return mTextView;
    }
}
