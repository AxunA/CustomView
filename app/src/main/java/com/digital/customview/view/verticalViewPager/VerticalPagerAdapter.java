package com.digital.customview.view.verticalViewPager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digital.customview.R;

/**
 * Created by fangzhengyou on 15/4/18.
 * 消息列表ViewAdapter
 */
public class VerticalPagerAdapter extends PagerAdapter {

    private LayoutInflater mInflater;
    private String[] mData;

    public VerticalPagerAdapter(Context context, String[] array) {
        this.mData = array;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View contentView = mInflater.inflate(R.layout.layout_item_shop_broadcast, container,false);
        MarqueeText textView = (MarqueeText) contentView.findViewById(R.id.tv_guide);
        textView.setText(mData[position]);

        container.addView(contentView);
        return contentView;
    }

}