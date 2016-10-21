package com.digital.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.digital.customview.view.DotProgressView;
import com.digital.customview.view.verticalViewPager.VerticalPagerAdapter;
import com.digital.customview.view.verticalViewPager.VerticalViewPager;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init DotProgress
        ((DotProgressView)findViewById(R.id.dot1)).startDelay(120);
        ((DotProgressView)findViewById(R.id.dot2)).startDelay(360);
        ((DotProgressView)findViewById(R.id.dot3)).startDelay(640);

        //init VerticalViewPager
        String [] array={TestData.STRING_1,TestData.STRING_2,TestData.STRING_3};
        VerticalPagerAdapter adapter = new VerticalPagerAdapter(this, array);
        VerticalViewPager verticalViewPager=(VerticalViewPager)findViewById(R.id.vvp_broadcast);
        verticalViewPager.setAdapter(adapter);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.dci_ac_info:
                    showToast(getString(R.string.account_info));
                break;
        }
    }

    private void showToast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
}
