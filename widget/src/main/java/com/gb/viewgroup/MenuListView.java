package com.gb.viewgroup;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.gb.viewgroup.util.StringUtils;
import com.gb.viewgroup.util.Unit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Create by wgb on 2019/4/16.
 */
public class MenuListView extends ListView {
    private Context mContext;
    private int mMenuId;
    private int mBorderStyle;
    private int mBorderColor;
    private int mBorderThickness;
    private boolean mItemClickable;
    private int mGroupMargin;
    private int mGroupMarginTop;
    private int mGroupMarginBottom;
    private int mItemMargin;
    private int mItemMarginTop;
    private int mItemMarginBottom;
    private boolean mUseRipple;

    private float defaultBorderThickness = 0.5f;
    private int defaultGroupMargin = 10;
    private int defaultItemMargin = 0;
    //    private int defaultBorderColor = 0xFFE8E8E8;//边框颜色
    private int defaultBorderColor = 0xFFE8E8E8;//边框颜色

    private Paint mBorderPaint;
    private MenuAdapter mMenuAdapter;

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
        defaultBorderThickness = Unit.dp2px(mContext, defaultBorderThickness);
        defaultGroupMargin = Unit.dp2px(mContext, defaultGroupMargin);
        defaultItemMargin = Unit.dp2px(mContext, defaultItemMargin);
        initAttrs(attrs);
        initPaint();
        initView();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.MenuListView);
        mMenuId = ta.getResourceId(R.styleable.MenuListView_menu, 0);
        mBorderColor = ta.getColor(R.styleable.MenuListView_mBorderColor, defaultBorderColor);
        mBorderStyle = ta.getInt(R.styleable.MenuListView_mBorderStyle, 0);
        mBorderThickness = ta.getDimensionPixelSize(R.styleable.MenuListView_mBorderThickness, (int) defaultBorderThickness);
        mItemClickable = ta.getBoolean(R.styleable.MenuListView_mItemClickable, false);
        mGroupMargin = ta.getDimensionPixelSize(R.styleable.MenuListView_mGroupMargin, defaultGroupMargin);
        mGroupMarginTop = ta.getDimensionPixelSize(R.styleable.MenuListView_mGroupMarginTop, 0);
        mGroupMarginBottom = ta.getDimensionPixelSize(R.styleable.MenuListView_mGroupMarginBottom, 0);
        mItemMargin = ta.getDimensionPixelSize(R.styleable.MenuListView_mItemMargin, defaultItemMargin);
        mItemMarginTop = ta.getDimensionPixelSize(R.styleable.MenuListView_mItemMarginTop, 0);
        mItemMarginBottom = ta.getDimensionPixelSize(R.styleable.MenuListView_mItemMarginBottom, 0);
        mUseRipple = ta.getBoolean(R.styleable.MenuListView_mUseRipple, true);
        ta.recycle();
    }

    @Override
    public boolean performItemClick(View view, int position, long id) {
        return super.performItemClick(view, position, id);
    }

    private void initView() {
        if (mMenuId != 0) {
            mMenuAdapter = new MenuAdapter(mContext, mMenuId);
            setAdapter(mMenuAdapter);
        }
    }

    private void initPaint() {
        mBorderPaint = new Paint();
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setStrokeWidth(mBorderThickness);
    }

    @Override
    public void setAdapter(ListAdapter listAdapter) {
        if (listAdapter == null) {
            return;
        }
        if (listAdapter instanceof MenuAdapter) {
            mMenuAdapter = (MenuAdapter) listAdapter;
        } else {
            mMenuAdapter = null;
            setAdapter(listAdapter);
        }
        MenuAdapterProxy proxy = new MenuAdapterProxy(mMenuAdapter) {
            @Override
            public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView != null) {
                    mMenuAdapter.getView(position, ((ViewGroup) convertView).getChildAt(0), parent);
                } else {
                    final View view = mMenuAdapter.getView(position, convertView, parent);

                    BorderLinearLayout layout = new BorderLinearLayout(mContext);
                    layout.setOrientation(LinearLayout.VERTICAL);
                    int itemMarginTop = mItemMarginTop;
                    int itemMarginBottom = mItemMarginBottom;
                    if (mItemMarginTop == 0) {
                        itemMarginTop = mItemMargin;
                    }
                    if (mItemMarginBottom == 0) {
                        itemMarginBottom = mItemMargin;
                    }
                    int currGroupId = mMenuAdapter.getItem(position).getGroupId();
                    final int prevGroupId = position - 1 >= 0 ? getItem(position - 1).getGroupId() : currGroupId;
                    int lastGroupId = position + 1 < getCount() ? getItem(position + 1).getGroupId() : currGroupId;
                    if (currGroupId != prevGroupId) {
                        itemMarginTop += mGroupMarginTop == 0 ? mGroupMargin : mGroupMarginTop;
                    }
                    if (currGroupId != lastGroupId) {
                        itemMarginBottom += mGroupMarginBottom == 0 ? mGroupMargin : mGroupMarginBottom;
                    }
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(0, itemMarginTop, 0, itemMarginBottom);
                    layout.setBorder(mBorderStyle, mBorderPaint, itemMarginTop, itemMarginBottom);
                    if (mUseRipple) {
                        //flag: solve the problem that click the blank border and the item take effect!
                        view.setClickable(true);
                        view.setBackgroundResource(R.drawable.selector_white);
                        //flag: add the view's click event
                        view.setOnClickListener(new OnClickListener() {
                            private Method mHandler;

                            @Override
                            public void onClick(View v) {
                                if (mItemClickable) {
                                    if (!StringUtils.isEmpty(mMenuAdapter.getItem(position).getDes())) {
                                        Class clazz = null;
                                        try {
                                            clazz = Class.forName(mMenuAdapter.getItem(position).getDes());
                                            mContext.startActivity(new Intent(mContext, clazz));
                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    String handlerName = mMenuAdapter.getItem(position).getOnClickFunc();
                                    if (!StringUtils.isEmpty(handlerName)) {
                                        if (mHandler == null) {
                                            try {
                                                mHandler = getContext().getClass().getMethod(handlerName, View.class);
                                            } catch (NoSuchMethodException e) {
                                                int id = getId();
                                                String idText = id == NO_ID ? "" : " with id '"
                                                        + getContext().getResources().getResourceEntryName(
                                                        id) + "'";
                                                throw new IllegalStateException("Could not find a method " +
                                                        handlerName + "(View) in the activity "
                                                        + getContext().getClass() + " for onClick handler"
                                                        + " on view " + View.class + idText, e);
                                            }
                                        }
                                        try {
                                            mHandler.invoke(getContext(), view);
                                        } catch (IllegalAccessException e) {
                                            throw new IllegalStateException("Could not execute non "
                                                    + "public method of the activity", e);
                                        } catch (InvocationTargetException e) {
                                            throw new IllegalStateException("Could not execute "
                                                    + "method of the activity", e);
                                        }
                                    }
                                }
                            }
                        });
                    }
                    layout.addView(view, params);
                    convertView = layout;
                }
                return convertView;
            }
        };
        super.setAdapter(proxy);
    }

    public void setMenuById(int menuId) {
        mMenuAdapter = new MenuAdapter(mContext, menuId);
        setAdapter(mMenuAdapter);
    }
}