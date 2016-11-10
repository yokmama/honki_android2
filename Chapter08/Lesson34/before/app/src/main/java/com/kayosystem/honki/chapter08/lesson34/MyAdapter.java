package com.kayosystem.honki.chapter08.lesson34;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class MyAdapter {

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

}
