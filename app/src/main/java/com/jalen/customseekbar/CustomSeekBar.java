package com.jalen.customseekbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by 于德海 on 2016/3/21.
 *
 * @decpter
 */
public class CustomSeekBar extends SeekBar implements SeekBar.OnSeekBarChangeListener{

    private Context mContext;
    private PopupWindow mPop;
    private TextView mTextView;
    private int start_x;
    private int pop_viewWidth;
    private View view;
    private int size_2,sizemin;//最小高度
    private int price;//当前出价
    private int bottom,paddingleft;//距离底部距离
    public CustomSeekBar(Context context) {
        this(context, null);
    }

    public CustomSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        view = LayoutInflater.from(mContext).inflate(R.layout.aty_auction_seekbar_pop,null,false);
        mTextView = (TextView) view.findViewById(R.id.tv_number);
        mPop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPop.setBackgroundDrawable(new BitmapDrawable());
        setOnSeekBarChangeListener(this);
        sizemin = ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 100, getResources().getDisplayMetrics()))/2;
        size_2 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
    }

    /***
     * 得到当前价格
     * @return
     */
    public int getNowPrice(){
        try {
            if(mTextView!=null)
                return Integer.parseInt(mTextView.getText().toString());
            else
                return 0;
        }catch (ClassCastException e){
//            ToastUtils.showToast("转换失败");
            return 0;
        }


    }




    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mTextView.setText(progress + "");
        mPop.update(getXPosition(seekBar), bottom, -1, -1);
        getXPosition(seekBar);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        start_x = getLeft();
        mPop.update(getXPosition(seekBar), bottom, -1, -1);

    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Log.d("yu", String.valueOf(hasWindowFocus));
        View parent = (View) getParent();
        paddingleft = parent.getPaddingLeft();
        int top = ((Activity) mContext).getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();//获取布局距离屏幕顶端的距离
        bottom = top+parent.getTop();
        mPop.showAtLocation(this, Gravity.LEFT | Gravity.TOP, getXPosition(this),bottom);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if(visibility!= View.VISIBLE){
            mPop.dismiss();
        }
        Log.d("yu", "WindowChange: " + visibility);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
    private int getXPosition(SeekBar seekBar){
        int newx = (int) (((double)seekBar.getProgress()/seekBar.getMax())*seekBar.getWidth());
        int textwidth = ((int) mTextView.getPaint().measureText(seekBar.getProgress() + ""))/2;
        int offwidth = seekBar.getThumbOffset()/2;
        if(sizemin>=(textwidth+size_2)){
            textwidth = sizemin;
        }else{
            textwidth = textwidth+size_2;
        }
        newx = getLeft()+newx-textwidth-offwidth+paddingleft;
        Log.d("yu", "newx:" + newx + " progerss:" + seekBar.getProgress() + " calue: " + (double) seekBar.getProgress() / seekBar.getMax());
        return newx;
    }
}
