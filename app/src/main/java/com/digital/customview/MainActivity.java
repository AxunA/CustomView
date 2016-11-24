package com.digital.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.digital.customview.view.DigitalBannerView;
import com.digital.customview.view.DotProgressView;
import com.digital.customview.view.verticalViewPager.VerticalPagerAdapter;
import com.digital.customview.view.verticalViewPager.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements DigitalBannerView.BannerItemClickListener {

    private ViewGroup mContainerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init DotProgress
        ((DotProgressView) findViewById(R.id.dot1)).startDelay(120);
        ((DotProgressView) findViewById(R.id.dot2)).startDelay(360);
        ((DotProgressView) findViewById(R.id.dot3)).startDelay(640);

        //init VerticalViewPager
        String[] array = {TestData.STRING_1, TestData.STRING_2, TestData.STRING_3};
        VerticalPagerAdapter adapter = new VerticalPagerAdapter(this, array);
        VerticalViewPager verticalViewPager = (VerticalViewPager) findViewById(R.id.vvp_broadcast);
        verticalViewPager.setAdapter(adapter);

        //init DigitalBannerView
        List<Integer> mImageAddressList = new ArrayList<>();
        mImageAddressList.add(R.drawable.banner_1);
        mImageAddressList.add(R.drawable.banner_2);
        DigitalBannerView bannerView = (DigitalBannerView) findViewById(R.id.bv_img);
        bannerView.initPageView(mImageAddressList, this);

        mContainerView=(ViewGroup)findViewById(R.id.ll_container);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dci_ac_info:
                showToast(getString(R.string.account_info));
                Intent intent = new Intent(this, TestActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_open_in, R.anim.activity_open_out);
                break;
            case R.id.dci_show_text:
                //实例化一个子View
                View newView = LayoutInflater.from(this).inflate(
                        R.layout.layout_animation_item, mContainerView, false);
                newView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContainerView.removeView(v);
                    }
                });
                mContainerView.addView(newView);
                int childCount=mContainerView.getChildCount();
                showToast(childCount+"");
                break;
        }
    }

    private void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBannerItemClick(int item) {
        showToast(String.valueOf(item));
    }
}
