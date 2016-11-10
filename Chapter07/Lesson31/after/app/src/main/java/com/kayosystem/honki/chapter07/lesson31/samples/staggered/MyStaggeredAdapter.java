package com.kayosystem.honki.chapter07.lesson31.samples.staggered;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kayosystem.honki.chapter07.lesson31.R;
import com.kayosystem.honki.chapter07.lesson31.holders.ImageViewHolder;
import com.kayosystem.honki.chapter07.lesson31.item.BaseItem;
import com.kayosystem.honki.chapter07.lesson31.item.ImageItem;

import java.util.List;

public class MyStaggeredAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BaseItem> mItems;

    public MyStaggeredAdapter(List<BaseItem> items) {
        mItems = items;
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        BaseItem item = mItems.get(position);

        ImageItem imageItem = (ImageItem) item;
        ImageViewHolder imageViewHolder = (ImageViewHolder) viewHolder;
        imageViewHolder.getTextView().setText(imageItem.getName());
        imageViewHolder.getImageView().setImageResource(imageItem.getId());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (R.id.big_image_type == viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.big_image_row, parent, false);
            return new ImageViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_image, parent, false);
            return new ImageViewHolder(v);
        }
    }

    @Override
    public int getItemCount() {
        if (mItems != null) {
            return mItems.size();
        } else {
            return 0;
        }
    }

}
