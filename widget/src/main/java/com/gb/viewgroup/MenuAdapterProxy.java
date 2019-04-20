package com.gb.viewgroup;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Create by wgb on 2019/4/17.
 */
public abstract class MenuAdapterProxy extends BaseAdapter {
    MenuAdapter mAdapter;

    public MenuAdapterProxy(MenuAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public abstract View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent);

    @Override
    public int getCount() {
        return mAdapter.getCount();
    }

    @Override
    public SuperMenuItem getItem(int position) {
        return mAdapter.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return mAdapter.getItemId(position);
    }
}
