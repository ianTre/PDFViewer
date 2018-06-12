package com.example.bishoppc.apppdfread;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SizeF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by BishopPC on 7/2/2018.
 */

public class TouchEventView extends View {

    private int width , height;
    public Bitmap mBitmap;
    public Canvas mCanvas;
    public Paint mPaint = new Paint();
    private Path mPath = new Path();
    private float mX, mY;
    private static final  float TOLERANCE = 5;
    Context context;

    public TouchEventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4f);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

    }

    private void startTouch(float x, float y ) {
        mPath.moveTo(x, y);
        mX = x;
        mY = y;

    }

    private void moveTouch(float x , float y )
    {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);

        if ( dx >= TOLERANCE || dy >= TOLERANCE)
        {
            mPath.quadTo(mX , mY , (x + mX) / 2 , ( y + mY) /2 );
            mX = x;
            mY = y;

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(mPath , mPaint);
    }

    public void clearCanvas()
    {
        mPath.reset();
        invalidate();
    }

    private void upTouch()
    {
        mPath.lineTo(mX,mY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                startTouch(x,y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x,y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;
        }
        return true;
    }
}
