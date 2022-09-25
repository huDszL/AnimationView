package com.hujun.animationdemo.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hujun.animationdemo.R;

public class MyView extends View {

    private String TAG = "MyView ";

    private int mRadius;
    private int mX;
    private int mY;
    private float mCenterX;
    private float mCenterY;
    private int moveDistance ;
    private int defaultDistance ;

    private RectF mRectF;

    private Paint mPaint ;
    private Paint textPaint ;
    private ValueAnimator valueAnimator ;
    private ValueAnimator circleAnimator ;
    private ObjectAnimator objectAnimator ;
    private AnimatorSet animatorSet  ;

    public MyView (Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG,"MyView ");
        animatorSet = new AnimatorSet();
        initPain();
    }

    private void initPain() {
        mPaint = new Paint();
        mRectF = new RectF();
        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(40f);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Log.i(TAG,"draw");

        //RectF设置
        mRectF.left = moveDistance;
        mRectF.top =0;
        mRectF.right = mX-moveDistance;
        mRectF.bottom = mY;

        //矩形画笔
        mPaint.setColor(Color.parseColor("#33dd33"));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(mRectF,mRadius, mRadius,mPaint);

        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float baseline = (fontMetrics.descent - fontMetrics.ascent)/2 + mCenterY - fontMetrics.descent  ;
        canvas.drawText("确认提交",mCenterX,baseline,textPaint);
        //画圆弧
        //startAngle 圆弧开始位置，从3点钟方向开始
        //sweepAngle 圆弧弧度
        //useCenter ,false为空心圆弧，true为实心（扇形）圆弧
//        canvas.drawArc(mRectF,0,180,false,mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG,"onSizeChanged ");
        mX = w;
        mY = h;
        mCenterX = mX/2;
        mCenterY = mY/2;
        defaultDistance = (mX-mY)/2 ;
        initAnimation();
    }

    private void rectToAngleAnimation(){
        valueAnimator = ValueAnimator.ofInt(0,mY/2);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRadius = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    private void rectToCircleAnimation(){
        circleAnimator = ValueAnimator.ofInt(0,defaultDistance);
        circleAnimator.setDuration(500);
        circleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                moveDistance = (int) animation.getAnimatedValue();
                int alpha = 255 - (moveDistance * 255) / defaultDistance ;
                Log.d("myview--------","alpha----"+alpha);

                textPaint.setAlpha(alpha);

                invalidate();
            }
        });
    }

    public void start(){
        animatorSet.start();
    }

    /**
     * 初始化所有动画
     */
    private void initAnimation() {

        rectToAngleAnimation();
        rectToCircleAnimation();
        animatorSet.play(valueAnimator).before(circleAnimator);

    }
}
