package com.gb.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Create by wgb on 2019/4/16.
 */
public class MenuViewHolder {
    private View mView;
    public ImageView imageView;
    public TextView textView;

    public MenuViewHolder(View view) {
        mView = view;
        imageView = mView.findViewById(R.id.icon);
        textView = mView.findViewById(R.id.text);
    }
}
