package com.kayosystem.honki.chapter07.lesson31.item;

import com.kayosystem.honki.chapter07.lesson31.R;

public class ImageItem extends BaseItem {
    private int mId;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    @Override
    public int getType() {
        return R.id.small_image_type;
    }
}
