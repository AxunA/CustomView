package com.digital.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.digital.customview.view.DotProgressView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((DotProgressView)findViewById(R.id.dot1)).startDelay(120);
        ((DotProgressView)findViewById(R.id.dot2)).startDelay(360);
        ((DotProgressView)findViewById(R.id.dot3)).startDelay(640);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.dci_ac_info:
                showToast(getString(R.string.click));
                break;
        }
    }

    private void showToast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
}
