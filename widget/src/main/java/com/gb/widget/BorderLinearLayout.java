package com.gb.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * Create by wgb on 2019/4/17.
 */
public class BorderLinearLayout extends LinearLayout {
    private int mBorderStyle;
    private Paint mPaint;
    private int mTopMargin;
    private int mBottomMargin;

    public BorderLinearLayout(Context context) {
        this(context, null);
    }

    public BorderLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BorderLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if ((mBorderStyle & Constants.BOARDER_LEFT) != 0) {
            canvas.drawLine(0, mTopMargin, 0, getHeight() - mBottomMargin, mPaint);
        }
        if ((mBorderStyle & Constants.BOARDER_TOP) != 0) {
            canvas.drawLine(0, mTopMargin, getWidth(), mTopMargin, mPaint);
        }
        if ((mBorderStyle & Constants.BOARDER_RIGHT) != 0) {
            canvas.drawLine(getWidth(), mTopMargin, getWidth(), getHeight() - mTopMargin, mPaint);
        }
        if ((mBorderStyle & Constants.BOARDER_BOTTOM) != 0) {
            canvas.drawLine(0, getHeight() - mBottomMargin, getWidth(), getHeight() - mBottomMargin, mPaint);
        }
    }

    public BorderLinearLayout setBorder(int borderStyle, Paint paint, int topMargin, int bottomMargin) {
        mBorderStyle = borderStyle;
        mPaint = paint;
        mTopMargin = topMargin;
        mBottomMargin = bottomMargin;
        invalidate();
        return this;
    }


}
