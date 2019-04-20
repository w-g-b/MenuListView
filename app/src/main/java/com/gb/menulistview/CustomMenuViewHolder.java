package com.gb.menulistview;

import android.view.View;

import com.gb.viewgroup.BaseMenuViewHolder;
import com.gb.viewgroup.IMenuViewHolder;
import com.gb.viewgroup.IMenuViewHolderFactory;

public final class CustomMenuViewHolder extends BaseMenuViewHolder {
    private CustomMenuViewHolder(View view) {
        textView = view.findViewById(R.id.text_title);
        imageView = view.findViewById(R.id.icon_right);
    }

    static class MenuViewHolderFactory implements IMenuViewHolderFactory {
        public MenuViewHolderFactory() {

        }

        public IMenuViewHolder createMenuViewHolder(View view) {
            return new CustomMenuViewHolder(view);
        }
    }
}