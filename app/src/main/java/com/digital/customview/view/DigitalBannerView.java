package com.digital.customview.view;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.digital.customview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fangzhengyou on 15/4/16.
 * 首页轮播图View
 */
public class DigitalBannerView extends LinearLayout {

    private final String TAG="DigitalBannerView";
    private Context mContext;

    private ViewPager mViewPager;
    private LinearLayout mDotsViewContainer;
    private BannerItemClickListener mBannerItemClickListener;

    private List<Integer> mImageAddressList;
    private List<View> mDotViewsList;
    private boolean isAutoPlay = true; // 自动轮播启用开关 默认自动轮播
    private int bannerSwitchTime=5000; // 自动轮播切换时间 默认5s

    public DigitalBannerView(Context context) {
        this(context, null);
    }

    public DigitalBannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DigitalBannerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext=context;
        LayoutInflater.from(context).inflate(R.layout.layout_banner_view,this);
        mViewPager =(ViewPager)findViewById(R.id.vp_banner);
        mDotsViewContainer =(LinearLayout)findViewById(R.id.ll_dots);
        mDotViewsList =new ArrayList<>();
    }

    /**
     * 初始化加载的图片
     * @param imageList 对外只接受List<Integer>型的地址列表
     * @param listener BannerItem点击监听
     */
    public void initPageView(List<Integer> imageList, BannerItemClickListener listener){
        if(imageList==null||imageList.size()==0)return;
        mImageAddressList =imageList;
        mBannerItemClickListener =listener;

        //init PageView
        initPageViews();

        //init DotsView
        initDotsViews();

        //开始轮播
        new SwitchPageTask().execute();
    }

    public void isAutoPlay(boolean isAutoPlay){
        this.isAutoPlay=isAutoPlay;
    }

    public void setBannerSwitchTime(int bannerSwitchTime) {
        this.bannerSwitchTime = bannerSwitchTime;
    }

    class SwitchPageTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            while (isAutoPlay){
                try {
                    Thread.sleep(bannerSwitchTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Log.d(TAG,"onProgressUpdate");
            mViewPager.getCurrentItem();
            int itemGoingToShow = (mViewPager.getCurrentItem() + 1) % mImageAddressList.size(); //最巧妙的方法在这里
            mViewPager.setCurrentItem(itemGoingToShow,true);
        }
    }

    private void initPageViews(){
        ImageViewPagerAdapter imageViewPagerAdapter=new ImageViewPagerAdapter();
        mViewPager.setAdapter(imageViewPagerAdapter);

        //DotsView根据PageView的变化而变化
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < mDotViewsList.size(); i++) {
                    if (i == position) {
                        mDotViewsList.get(i).setBackgroundResource(R.drawable.dot_enable);
                    } else {
                        mDotViewsList.get(i).setBackgroundResource(R.drawable.dot_disable);
                    }
                }
            }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initDotsViews(){
        mDotsViewContainer.removeAllViews();
        mDotViewsList.clear();
        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.leftMargin = 4;
        params.rightMargin = 4;
        for (int i = 0; i < mImageAddressList.size(); i++) {
            ImageView dotView = new ImageView(mContext);
            mDotsViewContainer.addView(dotView, params);
            mDotViewsList.add(dotView);
        }
    }

    public class ImageViewPagerAdapter extends PagerAdapter {

        public ImageViewPagerAdapter(){
        }

        @Override
        public int getCount() {
            return mImageAddressList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView imageView=new ImageView(mContext);
            //此处是加载图片部分，可选择加载本地图片或网络上的图片
            imageView.setImageResource(mImageAddressList.get(position));
            //RequestSender.getInstance(mContext).loadImage(mImageAddressList.get(position),imageView,R.drawable.defaul_pic_square);
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mBannerItemClickListener!=null)
                    mBannerItemClickListener.onBannerItemClick(position);
                }
            });

            container.addView(imageView);
            return imageView;
        }
    }

    public interface BannerItemClickListener{
        void onBannerItemClick(int item);
    }

}
