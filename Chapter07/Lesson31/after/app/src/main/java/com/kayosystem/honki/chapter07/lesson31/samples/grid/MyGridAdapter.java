package com.kayosystem.honki.chapter07.lesson31.samples.grid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kayosystem.honki.chapter07.lesson31.R;
import com.kayosystem.honki.chapter07.lesson31.holders.ImageViewHolder;
import com.kayosystem.honki.chapter07.lesson31.holders.IndexViewHolder;
import com.kayosystem.honki.chapter07.lesson31.item.BaseItem;
import com.kayosystem.honki.chapter07.lesson31.item.ImageItem;
import com.kayosystem.honki.chapter07.lesson31.item.IndexItem;

import java.util.List;


public class MyGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BaseItem> mItems;

    public MyGridAdapter(List<BaseItem> items) {
        mItems = items;
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        if (type == R.id.index_type) {
            return new IndexViewHolder(inflater.inflate(R.layout.row_index, viewGroup, false));
        } else {
            return new ImageViewHolder(inflater.inflate(R.layout.row_image, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        BaseItem item = mItems.get(position);
        if (item.getType() == R.id.index_type) {
            IndexItem indexItem = (IndexItem) item;
            IndexViewHolder indexViewHolder = (IndexViewHolder) viewHolder;
            indexViewHolder.getTextView().setText(indexItem.getName());
        } else {
            ImageItem imageItem = (ImageItem) item;
            ImageViewHolder imageViewHolder = (ImageViewHolder) viewHolder;
            imageViewHolder.getTextView().setText(imageItem.getName());
            imageViewHolder.getImageView().setImageResource(imageItem.getId());
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

