package com.jalen.customseekbar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by 于德海 on 2016/3/22.
 *
 * @decpter
 */
public class CustomAuctionTextView extends TextView {

    public CustomAuctionTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
    }

    public CustomAuctionTextView(Context context) {
        this(context, null);
    }

    public CustomAuctionTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("yu", event.getAction() + "");
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(R.drawable.aty_auction_shape_bg_auction);
                setTextColor(Color.WHITE);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                setBackgroundResource(R.drawable.aty_auction_shape_bg_f2f2f2);
                setTextColor(Color.BLACK);
                performClick();
                break;
        }
        return true;
    }
}
