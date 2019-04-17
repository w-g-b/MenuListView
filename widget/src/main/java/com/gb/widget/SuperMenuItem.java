package com.gb.widget;

import android.graphics.drawable.Drawable;
import android.view.MenuItem;

/**
 * Create by wgb on 2019/4/17.
 */
public class SuperMenuItem {
    private MenuItem mMenuItem;
    private String des;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public SuperMenuItem(MenuItem menuItem) {
        mMenuItem = menuItem;
    }

    public MenuItem getMenuItem() {
        return mMenuItem;
    }

    public void setMenuItem(MenuItem mMenuItem) {
        this.mMenuItem = mMenuItem;
    }

    public Drawable getIcon() {
        return mMenuItem.getIcon();
    }

    public CharSequence getTitle() {
        return mMenuItem.getTitle();
    }

    public int getGroupId() {
        return mMenuItem.getGroupId();
    }

    public int getItemId() {
        return mMenuItem.getItemId();
    }
}
