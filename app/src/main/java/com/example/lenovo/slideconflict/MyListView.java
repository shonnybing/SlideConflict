package com.example.lenovo.slideconflict;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.ListView;

/**
 * Created by Lenovo on 2018/1/7.
 */

public class MyListView extends ListView {
    private HorizontalViewGroup2 parentView;
    private int currentX;
    private int currentY;
    private int lastX;
    private int lastY;
    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setParentView(HorizontalViewGroup2 parentView) {
        this.parentView = parentView;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        currentX = (int) ev.getRawX();
        currentY = (int) ev.getRawY();
        switch(ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                parentView.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(lastX - currentX) > Math.abs(lastY - currentY)) {
                    parentView.requestDisallowInterceptTouchEvent(false);
                }
                break;
        }
        lastX = currentX;
        lastY = currentY;
        return super.dispatchTouchEvent(ev);
    }
}
