package com.kayosystem.honki.chapter07.lesson31.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kayosystem.honki.chapter07.lesson31.R;

public class ImageViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnLongClickListener {
    private ImageViewHolderListener mListener;

    private ImageView mImageView;
    private TextView mTextView;

    public ImageViewHolder(View itemView) {
        super(itemView);

        mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        mTextView = (TextView) itemView.findViewById(R.id.textView);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setListener(ImageViewHolderListener listener) {
        mListener = listener;
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public TextView getTextView() {
        return mTextView;
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            mListener.onClick(this);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (mListener != null) {
            return mListener.onLongClick(this);
        }
        return false;
    }

    public interface ImageViewHolderListener {
        void onClick(ImageViewHolder holder);
        boolean onLongClick(ImageViewHolder holder);
    }
}
