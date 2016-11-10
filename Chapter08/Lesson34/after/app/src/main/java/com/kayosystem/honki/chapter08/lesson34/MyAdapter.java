package com.kayosystem.honki.chapter08.lesson34;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

public class MyAdapter extends RealmBaseAdapter<Cat> implements ListAdapter {

    private Context mContext;
    private DeleteListener mListener;

    private static class ViewHolder {
        TextView cat;
        ImageView delete;
    }

    public void setCallback(DeleteListener callback){
        mListener = callback;
    }

    public interface DeleteListener {
        void delete(long catId);
    }

    public MyAdapter(Context context, OrderedRealmCollection<Cat> cats) {
        super(context, cats);
        this.mContext = context;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.cat = (TextView) convertView.findViewById(R.id.txtName);

            ImageView delete = (ImageView) convertView.findViewById(R.id.imageDelete);
            delete.setTag(getItemId(position));
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.delete((long) v.getTag());
                }
            });
            viewHolder.delete = delete;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.delete.setTag(getItemId(position));
        }

        viewHolder.cat.setText(getItem(position).getName());
        return convertView;
    }
}
