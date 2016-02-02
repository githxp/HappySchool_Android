package com.hxp.happyschool.beans;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by hxp on 16-2-2.
 */
public class StudyBean {


    private String mItemName;
    private int mImageResource;


    public int getmImageResource() {
        return mImageResource;
    }

    public void setmImageResource(int mImageResource) {
        this.mImageResource = mImageResource;
    }

    public String getmItemName() {
        return mItemName;
    }

    public void setmItemName(String mItemName) {
        this.mItemName = mItemName;
    }
}
