package com.digital.customview.view.verticalViewPager;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewDebug;
import android.widget.TextView;

/**
 * Created by fangzhengyou on 15/8/12.
 * 跑马灯View ，用于首页消息栏
 */
public class MarqueeText extends TextView {
    public MarqueeText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if(hasWindowFocus) super.onWindowFocusChanged(hasWindowFocus);
    }

    @Override
    @ViewDebug.ExportedProperty(category = "focus")
    public boolean isFocused() {
        return true;
    }
}
