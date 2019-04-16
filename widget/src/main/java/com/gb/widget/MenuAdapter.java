package com.gb.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuBuilder;

/**
 * Create by wgb on 2019/4/16.
 */
@SuppressLint("RestrictedApi")
public class MenuAdapter extends BaseAdapter {
    private int mResourceId;
    private Context mContext;
    private MenuBuilder menu;


    public MenuAdapter(Context context, int menuId) {
        this(context, menuId, R.layout.common_menu_item);
    }

    public MenuAdapter(Context context, int menuId, int resourceId) {
        mResourceId = resourceId;
        menu = new MenuBuilder(context);
        new MenuInflater(context).inflate(menuId, menu);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        MenuViewHolder holder;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(mResourceId, parent, false);
            holder = new MenuViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (MenuViewHolder) view.getTag();
        }
        holder.imageView.setImageDrawable(menu.getItem(position).getIcon());
        holder.textView.setText(menu.getItem(position).getTitle());
        return view;
    }


    @Override
    public int getCount() {
        return menu.size();
    }

    @Override
    public Object getItem(int position) {
        return menu.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return menu.getItem(position).getItemId();
    }
}
