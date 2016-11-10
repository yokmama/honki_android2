package com.kayosystem.honki.chapter07.lesson31.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kayosystem.honki.chapter07.lesson31.R;

public class ImageViewHolder extends RecyclerView.ViewHolder
        // TODO:レッスンではここにプログラムを追加
{
	
    // TODO:レッスンではここにプログラムを追加

    private ImageView mImageView;
    private TextView mTextView;

    public ImageViewHolder(View itemView) {
        super(itemView);

        mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        mTextView = (TextView) itemView.findViewById(R.id.textView);

        // TODO:レッスンではここにプログラムを追加
    }
    
    // TODO:レッスンではここにプログラムを追加
    
    public ImageView getImageView() {
        return mImageView;
    }

    public TextView getTextView() {
        return mTextView;
    }

    // TODO:レッスンではここにプログラムを追加
}