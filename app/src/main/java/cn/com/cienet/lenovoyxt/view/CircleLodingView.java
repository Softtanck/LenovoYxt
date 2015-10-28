package cn.com.cienet.lenovoyxt.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 7/22/2015
 */
public class CircleLodingView extends View {
    private int mWidth;
    private int mHeight;
    private boolean mIsStart;
    private int mRefreshStart = 90;
    private int mRefreshStop;
    private Paint mOutPaint;
    private RectF rectF;
    private ValueAnimator valueAnimator;
    private int mRidus;

    public CircleLodingView(Context context) {
        this(context, null);
    }

    public CircleLodingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleLodingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    @SuppressLint("NewApi")
    public CircleLodingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
//        initView();
    }

    private void initView() {
        mOutPaint = new Paint();
        mOutPaint.setStyle(Paint.Style.STROKE);
        mOutPaint.setColor(Color.parseColor("#00AAE9"));
        mOutPaint.setAntiAlias(true);
        mOutPaint.setStrokeWidth(3);

        valueAnimator = ValueAnimator.ofFloat(0, 60).setDuration(12000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                invalidate();
            }
        });

        valueAnimator.setRepeatCount(-1);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mRidus = mWidth < mHeight ? mWidth : mHeight;
        rectF = new RectF(50, 50, mRidus - 50, mRidus - 50);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mRefreshStart += mIsStart ? 3 : 10;
        mRefreshStop += mIsStart ? 10 : 3;
        mRefreshStart = mRefreshStart % 360;
        mRefreshStop = mRefreshStop % 360;

        int swipe = mRefreshStop - mRefreshStart;
        swipe = swipe < 0 ? swipe + 360 : swipe;

        canvas.drawArc(rectF,
                mRefreshStart, swipe, false, mOutPaint);
        if (swipe >= 330) {
            mIsStart = false;
        } else if (swipe <= 10) {
            mIsStart = true;
        }
    }
}