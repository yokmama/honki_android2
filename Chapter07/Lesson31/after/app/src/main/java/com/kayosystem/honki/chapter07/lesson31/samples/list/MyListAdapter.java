package com.kayosystem.honki.chapter07.lesson31.samples.list;

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

public class MyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements ImageViewHolder.ImageViewHolderListener {
    private List<BaseItem> mItems;

    public MyListAdapter(List<BaseItem> items) {
        // 表示するアイテム一覧を受け取る
        mItems = items;
    }

    @Override
    public int getItemViewType(int position) {
        // アイテムの種別を受け取る
        return mItems.get(position).getType();
    }

    @Override
    public int getItemCount() {
        // メソッドを実装して、アイテム数をRecyclerViewに伝える
        if (mItems != null) {
            return mItems.size();
        } else {
            return 0;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        // 中身のViewとViewHolderを作成する
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        if (type == R.id.index_type) {
            return new IndexViewHolder(inflater.inflate(R.layout.row_index, viewGroup, false));
        } else {
            return new ImageViewHolder(inflater.inflate(R.layout.row_list, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        // 中身のViewに値を設定する
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

            imageViewHolder.setListener(this);
        }
    }

    @Override
    public void onClick(ImageViewHolder holder) {
    }

    @Override
    public boolean onLongClick(ImageViewHolder holder) {
        return false;
    }
}

