package com.gb.viewgroup;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Create by wgb on 2019/4/16.
 * 自定义item的布局时，可以继承自BaseMenuViewHolder
 * 并且在重写传入View的构造方法，在构造方法中实例化imageView和textView
 */

public abstract class BaseMenuViewHolder implements IMenuViewHolder {
    protected ImageView imageView;
    protected TextView textView;


    protected BaseMenuViewHolder() {

    }

    @Override
    public TextView getTextView() {
        return textView;
    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }
}


