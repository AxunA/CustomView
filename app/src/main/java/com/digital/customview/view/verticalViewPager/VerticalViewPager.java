package com.digital.customview.view.verticalViewPager;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by fangzhengyou on 15/11/30.
 * 垂直翻页的ViewPager
 *
 */
public class VerticalViewPager extends ViewPager {

    private UIHandler mHandler;
    private int mCurrentItem = 0;
    private long mTransformPeriod=8;//切换间隔时间，8秒切换一次

    public VerticalViewPager(Context context) {
        this(context, null);
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPageTransformer(false, new DefaultTransformer()); //设置页面Pager切换模式
        mHandler =new UIHandler();
        startPlay();
    }

    /**
     * 开始轮播图自动切换
     */
    private void startPlay() {
        ScheduledExecutorService mScheduled = Executors.newSingleThreadScheduledExecutor();
        //四个参数：任务、开始时间、相隔时间、时间单位
        mScheduled.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    mHandler.obtainMessage().sendToTarget();
                }
            }
        }, mTransformPeriod, mTransformPeriod, TimeUnit.SECONDS);
    }

    private class UIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mCurrentItem =getCurrentItem();
            if(mCurrentItem == (getAdapter().getCount()-1)){
                setCurrentItem(0,true);
            }else {
                mCurrentItem = mCurrentItem +1;
                setCurrentItem(mCurrentItem,true);
            }
        }
    }

    private MotionEvent swapTouchEvent(MotionEvent event) {
        float width = getWidth();
        float height = getHeight();
        float swappedX = (event.getY() / height) * width;
        float swappedY = (event.getX() / width) * height;
        event.setLocation(swappedX, swappedY);
        return event;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercept = super.onInterceptTouchEvent(swapTouchEvent(event));
        //If not intercept, touch event should not be swapped.
        swapTouchEvent(event);
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(swapTouchEvent(ev));
    }

}
