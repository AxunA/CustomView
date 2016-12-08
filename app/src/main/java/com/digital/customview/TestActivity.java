package com.digital.customview;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;


public class TestActivity extends AppCompatActivity {

    private FlexboxLayout mFlexboxLayout;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mFlexboxLayout=(FlexboxLayout)findViewById(R.id.flex);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }else if (id == R.id.menu_item_add_item) {
            View view= LayoutInflater.from(this).inflate(R.layout.layout_item_record,null);
            TextView textView=(TextView)view.findViewById(R.id.tv_record_item);
            textView.setText(String.valueOf(num+=1024));
            mFlexboxLayout.addView(view);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_close_in,R.anim.activity_close_out);
    }

}
