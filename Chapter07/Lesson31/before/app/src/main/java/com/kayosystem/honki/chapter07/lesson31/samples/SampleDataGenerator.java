package com.kayosystem.honki.chapter07.lesson31.samples;

import android.content.Context;

import com.kayosystem.honki.chapter07.lesson31.R;
import com.kayosystem.honki.chapter07.lesson31.item.BaseItem;
import com.kayosystem.honki.chapter07.lesson31.item.BigImageItem;
import com.kayosystem.honki.chapter07.lesson31.item.ImageItem;
import com.kayosystem.honki.chapter07.lesson31.item.IndexItem;

import java.util.ArrayList;
import java.util.List;

public final class SampleDataGenerator {

    private static final int[] DOG_RES_IDS = new int[]{
            R.drawable.dog1,
            R.drawable.dog2,
            R.drawable.dog3,
            R.drawable.dog4,
            R.drawable.dog5,
            R.drawable.dog6,
            R.drawable.dog7,
            R.drawable.dog8,
            R.drawable.dog9,
            R.drawable.dog10,
            R.drawable.dog11,
            R.drawable.dog12,
            R.drawable.dog13,
            R.drawable.dog14,
            R.drawable.dog15,
            R.drawable.dog16,
            R.drawable.dog17,
            R.drawable.dog18,
            R.drawable.dog19,
            R.drawable.dog20,
            R.drawable.dog21,
    };

    private final Context mContext;

    public SampleDataGenerator(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    public List<BaseItem> generateIndexList() {
        List<BaseItem> list = new ArrayList<>();
        for (int i = 0; i < DOG_RES_IDS.length; i++) {
            int dogResId = DOG_RES_IDS[i];
            // ５個単位で目次を生成
            if (i % 3 == 0) {
                IndexItem item = new IndexItem();
                item.setName("Index Title[" + i + "]");
                list.add(item);
            }
            ImageItem item = new ImageItem();
            item.setId(dogResId);
            item.setName("DOG " + i);
            list.add(item);
        }

        return list;
    }

    public List<BaseItem> generateStaggeredList() {
        List<BaseItem> list = new ArrayList<>();
        for (int i = 0; i < DOG_RES_IDS.length; i++) {
            int dogResId = DOG_RES_IDS[i];

            //３個ずつ並べるうち、一つ目を大きい画像に設定
            if (i % 3 == 0) {
                BigImageItem item = new BigImageItem();
                item.setId(dogResId);
                item.setName("DOG " + i);
                list.add(item);
            } else {
                ImageItem item = new ImageItem();
                item.setId(dogResId);
                item.setName("DOG " + i);
                list.add(item);
            }
        }

        return list;
    }

}
