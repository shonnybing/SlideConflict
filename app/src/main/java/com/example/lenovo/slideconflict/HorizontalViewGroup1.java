package com.example.lenovo.slideconflict;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Lenovo on 2018/1/2.
 */

public class HorizontalViewGroup1 extends ViewGroup {
    private int leftBorder;
    private int rightBorder;
    private int currentX;
    private int currentY;
    private int lastX;
    private int lastY;
    private Scroller mScroller;

    public HorizontalViewGroup1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalViewGroup1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(getContext());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);  // 要调一次，不然子View的宽高不会测量
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                // 放置子View的位置
                child.layout(i*child.getMeasuredWidth(), 0, (i+1)*child.getMeasuredWidth(), child.getMeasuredHeight());
            }
            leftBorder = getChildAt(0).getLeft();
            rightBorder = getChildAt(childCount - 1).getRight();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        currentX = (int) ev.getRawX();
        currentY = (int) ev.getRawY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(lastX - currentX) > Math.abs(lastY - currentY)) {
                    return true;
                }
        }
        lastX = currentX;
        lastY = currentY;
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentX = (int) event.getRawX();
        currentY = (int) event.getRawY();
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                // 滑动到左边界时，不让滑动
                int distance = lastX - currentX;
                lastX = currentX;
                if (getScrollX() + distance < leftBorder) {
                    scrollTo(leftBorder, 0);
                    return true;
                }
                // 滑动到右边界时，不让滑动
                if (getScrollX() + distance + getWidth() > rightBorder) {
                    scrollTo(rightBorder - getWidth(), 0);
                    return true;
                }
                scrollBy(distance, 0);
                break;
            case MotionEvent.ACTION_UP:
                int page = (getScrollX() + getWidth() / 2) / getWidth();
                mScroller.startScroll(getScrollX(), getScrollY(), page * getWidth() - getScrollX(), 0);
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
