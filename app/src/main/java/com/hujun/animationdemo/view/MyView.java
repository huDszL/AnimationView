package com.hujun.animationdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyView extends View {

    private String TAG = "MyView ";

    private int mRadius;
    private int mX;
    private int mY;
    private float mCenterX;
    private float mCenterY;

    private RectF mRectF;

    private Paint mPaint ;

    public MyView (Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG,"MyView ");
        mPaint = new Paint();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Log.i(TAG,"draw");

        //RectF设置
        mRectF.left = mCenterX - mRadius/2;
        mRectF.top =mCenterY - mRadius;
        mRectF.right = mCenterX + mRadius;
        mRectF.bottom = mCenterY + mRadius;

        //矩形画笔
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(20);
        mPaint.setStyle(Paint.Style.STROKE);
        //画矩形
        canvas.drawRect(mRectF,mPaint);

        //圆弧画笔
        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(20);
        mPaint.setStyle(Paint.Style.STROKE);
        //画圆弧
        //startAngle 圆弧开始位置，从3点钟方向开始
        //sweepAngle 圆弧弧度
        //useCenter ,false为空心圆弧，true为实心（扇形）圆弧
        canvas.drawArc(mRectF,0,180,false,mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG,"onSizeChanged ");
        mX = w;
        mY = h;
        mRadius = w/4;
        mCenterX = mX/2;
        mCenterY = mY/2;
    }
}
