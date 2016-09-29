package com.digital.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.digital.customview.R;


/**
 * Created by fangzhengyou on 16/7/27.
 * 自定义点击栏
 * 默认显示arrow right
 */
public class DigitalClickItem extends FrameLayout {

    private ImageView mLeftIconIv;
    private TextView mLeftTextTv;
    private TextView mRightTextTv;
    private ImageView mRightIconIv;
    private View mMiddleLineV;

    public DigitalClickItem(Context context) {
        super(context);
    }

    public DigitalClickItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_digital_comm_click_item, this);
        mLeftIconIv = (ImageView) view.findViewById(R.id.iv_left_icon);
        mLeftTextTv = (TextView) view.findViewById(R.id.tv_left_text);
        mRightTextTv = (TextView) view.findViewById(R.id.tv_right_text);
        mRightIconIv = (ImageView) view.findViewById(R.id.iv_right_icon);
        mMiddleLineV = view.findViewById(R.id.v_middle_line);

        initAttrs(attrs);
    }

    public DigitalClickItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DigitalClickItem);
        try {
            Drawable mDrawableLeft = typedArray.getDrawable(R.styleable.DigitalClickItem_left_icon);
            Drawable mDrawableRight = typedArray.getDrawable(R.styleable.DigitalClickItem_right_icon);
            String mStringLeft = typedArray.getString(R.styleable.DigitalClickItem_left_text);
            String mStringRight = typedArray.getString(R.styleable.DigitalClickItem_right_text);
            Boolean isShowLine = typedArray.getBoolean(R.styleable.DigitalClickItem_isShowLine, false);
            Boolean isShowRightIcon = typedArray.getBoolean(R.styleable.DigitalClickItem_isShowRightIcon, true);

            if (mDrawableLeft != null) {
                mLeftIconIv.setImageDrawable(mDrawableLeft);
            } else {
                mLeftIconIv.setVisibility(GONE);
            }

            if (mDrawableRight != null)
                mRightIconIv.setImageDrawable(mDrawableRight);
            if (mStringLeft != null)
                mLeftTextTv.setText(mStringLeft);
            if (mStringLeft != null)
                mRightTextTv.setText(mStringRight);

            if (isShowLine) mMiddleLineV.setVisibility(View.VISIBLE);
            if (!isShowRightIcon) mRightIconIv.setVisibility(INVISIBLE);
        } finally {
            typedArray.recycle();
        }

    }

    public void setIvLeftIcon(int res) {
        mLeftIconIv.setImageResource(res);
    }

    public void setTvLeftText(String content) {
        mLeftTextTv.setText(content);
    }

    public void setIvRightIcon(int res) {
        mRightIconIv.setImageResource(res);
    }

    public void setTvRightText(String content) {
        mRightTextTv.setText(content);
    }

    public void setMiddleLineVisibility(int visibility) {
        mMiddleLineV.setVisibility(visibility);
    }
}
