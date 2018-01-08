package com.example.lenovo.slideconflict;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Lenovo on 2018/1/7.
 */

public class HorizontalViewGroup2 extends ViewGroup {

    private int leftBorder;
    private int rightBorder;
    private int currentX;
    private int lastX;
    private Scroller mScroller;

    public HorizontalViewGroup2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalViewGroup2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(getContext());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                child.layout(i*child.getMeasuredWidth(), 0, (i+1)*child.getMeasuredWidth(), child.getMeasuredHeight());
            }
            leftBorder = getChildAt(0).getLeft();
            rightBorder = getChildAt(childCount - 1).getRight();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        lastX = (int) ev.getRawX();
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentX = (int) event.getRawX();
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
