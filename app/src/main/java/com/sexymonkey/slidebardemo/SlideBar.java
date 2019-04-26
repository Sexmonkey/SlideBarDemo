package com.sexymonkey.slidebardemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class SlideBar extends View {
    private static final String TAG = "SlideBar";

    private final String [] SECTIONS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"
            , "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};


    private Paint mPaint;
    private Context mContext;
    private float mTextHeight;
    private int mWtih;
    private float mBaseLine;
    private float mX;
    private  SliderListenter mSliderListenter;
    private int mCurrentIndex = -1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public SlideBar(Context context) {
        this(context ,null);
        mContext = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public SlideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(sp2px(10));

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTextHeight= h * 1.0f / SECTIONS.length;
        mWtih = w;
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        mBaseLine = mTextHeight/2 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent)/2;
        mX = mWtih * 1f / 2 + mWtih * 1f / 4 ;
        Log.e(TAG,"mX === " + mX);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float baseLine = mBaseLine;
        for (int i = 0; i < SECTIONS.length; i++) {
            canvas.drawText(SECTIONS[i],mX ,baseLine,mPaint);
            baseLine += mTextHeight;
        }
    }

    public int sp2px(float sp) {
        float density = getResources().getDisplayMetrics().scaledDensity;
        return (int)(sp * density + 0.5F);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if(event.getX() < mX){
                    return false;
                }

                notifySectionChanged(event);
                break;
            case MotionEvent.ACTION_MOVE:
                notifySectionChanged(event);
                break;
            case MotionEvent.ACTION_UP:
                notifySectionChanged(event);
                mSliderListenter.onFinishSliding();
                break;
        }
        return true;
    }

    private void notifySectionChanged(MotionEvent event) {
        int index = (int) (event.getY() / mTextHeight);
        if(index <0){
            index = 0;
        }
        if(index > SECTIONS.length - 1){
            index = SECTIONS.length - 1;
        }

        if(mCurrentIndex != index){
            mCurrentIndex = index;
            mSliderListenter.onSliding(mCurrentIndex,SECTIONS[mCurrentIndex]);
        }
    }

    public void setSliderListenter(SliderListenter sliderListenter) {
        mSliderListenter = sliderListenter;
    }

    public interface SliderListenter{
        void onSliding(int index, String section);
        void onFinishSliding();
    }

}
