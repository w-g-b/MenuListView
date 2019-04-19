package com.gb.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Create by wgb on 2019/4/16.
 */
@SuppressLint("RestrictedApi")
public class MenuAdapter extends BaseAdapter {
    private int mResourceId;
    private Context mContext;
    private SuperMenu menu;
    private IMenuViewHolderFactory mMenuViewHolderFactory;


    public MenuAdapter(Context context, int menuId) {
        this(context, menuId, R.layout.common_menu_item, new CommonMenuViewHolder.MenuViewHolderFactory());
    }

    public MenuAdapter(Context context, int menuId, int resourceId, IMenuViewHolderFactory menuViewHolderFactory) {
        mResourceId = resourceId;
        mMenuViewHolderFactory = menuViewHolderFactory;
        menu = new SuperMenu(context, menuId);
        mContext = context;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        IMenuViewHolder holder;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(mResourceId, parent, false);
            holder = mMenuViewHolderFactory.createMenuViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (BaseMenuViewHolder) view.getTag();
        }
        holder.getImageView().setImageDrawable(menu.getItem(position).getIcon());
        holder.getTextView().setText(menu.getItem(position).getTitle());
        return view;
    }


    @Override
    public int getCount() {
        return menu.size();
    }

    @Override
    public SuperMenuItem getItem(int position) {
        return menu.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return menu.getItem(position).getItemId();
    }
}
