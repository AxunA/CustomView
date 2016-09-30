package com.digital.customview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.digital.customview.R;


/**
 * Created by fangzhengyou on 16/8/29.
 * 自定义dot
 */
public class DotProgressView extends View {

    private Paint mPaint;
    private float mRadius;
    private float mSize;

    public DotProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray=getContext().obtainStyledAttributes(attrs, R.styleable.DotProgressView);
        try {
            int height=typedArray.getLayoutDimension(R.styleable.DotProgressView_android_layout_height,-1);
            //int width=typedArray.getLayoutDimension(R.styleable.DotProgressView_android_layout_width,-1);
            Integer color=typedArray.getColor(R.styleable.DotProgressView_dot_color,Color.WHITE);

            mPaint=new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setColor(color);
            mSize=height/2;
        }finally {
            typedArray.recycle();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mPaint!=null){
            canvas.drawCircle(getWidth()/2, getHeight()/2, mRadius, mPaint);
        }
    }

    public void startDelay(long startDelay) {
        ValueAnimator animator = ValueAnimator.ofFloat(mSize/4, mSize);
        animator.setDuration(750);
        animator.setStartDelay(startDelay);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRadius = (Float)animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

}
