package com.gb.menulistview;

import android.view.View;

import com.gb.widget.BaseMenuViewHolder;
import com.gb.widget.IMenuViewHolder;
import com.gb.widget.IMenuViewHolderFactory;

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