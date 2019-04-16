package com.gb.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Create by wgb on 2019/4/16.
 */
public class MenuListView extends ListView {
    private Context mContext;
    private int mMenuId;

    public MenuListView(Context context) {
        super(context);
    }

    public MenuListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setDivider(null);
        setSelector(new ColorDrawable(Color.parseColor("#00ffffff")));
        mContext = context;
        initAttrs(attrs);
        initView();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.MenuListView);
        mMenuId = ta.getResourceId(R.styleable.MenuListView_menu, 0);
        ta.recycle();
    }

    private void initView() {
        MenuAdapter menuAdapter = new MenuAdapter(mContext, mMenuId);
        setAdapter(menuAdapter);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
    }
}
