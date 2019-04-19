package com.gb.widget;

import android.view.View;

/**
 * Create by wgb on 2019/4/17.
 */
public final class CommonMenuViewHolder extends BaseMenuViewHolder {
    private CommonMenuViewHolder(View view) {
        textView = view.findViewById(R.id.text);
        imageView = view.findViewById(R.id.icon);
    }

    public static class MenuViewHolderFactory implements IMenuViewHolderFactory {
        public MenuViewHolderFactory() {

        }

        public IMenuViewHolder createMenuViewHolder(View view) {
            return new CommonMenuViewHolder(view);
        }

    }
}
