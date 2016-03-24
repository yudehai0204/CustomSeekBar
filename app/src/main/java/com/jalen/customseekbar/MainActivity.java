package com.jalen.customseekbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomSeekBar mSeekBar;
    private ImageView img_jia,img_jian;
    private CustomAuctionTextView tv_jia1,tv_jia10,tv_jia100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initOnclick();
    }

    private void initOnclick() {
        img_jia.setOnClickListener(this);
        img_jian.setOnClickListener(this);
        tv_jia1.setOnClickListener(this);
        tv_jia10.setOnClickListener(this);
        tv_jia100.setOnClickListener(this);
    }

    private void initViews() {
        mSeekBar = (CustomSeekBar) findViewById(R.id.mSeekbar);
        img_jia = (ImageView) findViewById(R.id.img_jia);
        img_jian = (ImageView) findViewById(R.id.img_jian);
        tv_jia1 = (CustomAuctionTextView)findViewById(R.id.tv_jia1);
        tv_jia10 = (CustomAuctionTextView) findViewById(R.id.tv_jia10);
        tv_jia100 = (CustomAuctionTextView) findViewById(R.id.tv_jia100);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_jia:
                if(mSeekBar==null)
                    return;
                mSeekBar.setProgress(mSeekBar.getNowPrice()+1);
                break;
            case R.id.img_jian:
                if(mSeekBar==null)
                    return;
                mSeekBar.setProgress(mSeekBar.getNowPrice()-1);
                break;
            case R.id.tv_jia1://jia1
                if(mSeekBar==null)
                    return;
                mSeekBar.setProgress(mSeekBar.getNowPrice()+1);
                break;
            case R.id.tv_jia10://10
                if(mSeekBar==null)
                    return;
                mSeekBar.setProgress(mSeekBar.getNowPrice()+10);
                break;
            case R.id.tv_jia100://100
                if(mSeekBar==null)
                    return;
                mSeekBar.setProgress(mSeekBar.getNowPrice()+100);
                break;
        }
    }
}
