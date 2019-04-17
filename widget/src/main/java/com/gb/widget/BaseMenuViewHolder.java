package com.gb.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Create by wgb on 2019/4/16.
 * 自定义item的布局时，可以继承自BaseMenuViewHolder
 * 并且在重写传入View的构造方法，在构造方法中实例化imageView和textView
 */

public class BaseMenuViewHolder implements IMenuViewHolder {
    protected ImageView imageView;
    protected TextView textView;

    private BaseMenuViewHolder(View view) {
        imageView = view.findViewById(R.id.icon);
        textView = view.findViewById(R.id.text);
    }

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

    static class MenuViewHolderFactory implements IMenuViewHolderFactory {
        public MenuViewHolderFactory() {

        }

        public IMenuViewHolder createMenuViewHolder(View view) {
            return new BaseMenuViewHolder(view);
        }
    }

}
