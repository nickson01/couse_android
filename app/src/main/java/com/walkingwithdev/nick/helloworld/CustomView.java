package com.walkingwithdev.nick.helloworld;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Nick on 5/20/2016 AD.
 */
public class CustomView extends View {
    private boolean isBlue = false;
    private boolean isDown = false;
    private GestureDetector gestureDetector;

    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initWithAtts(attrs, 0, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initWithAtts(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
        initWithAtts(attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        gestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {

                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                isBlue = !isBlue;
                invalidate();
                return true;
            }
        });
        //เปิด mode clickable
        setClickable(true);

        //Handle click
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initWithAtts(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomView,
                defStyleAttr,
                defStyleRes);
        try {
            isBlue = a.getBoolean(R.styleable.CustomView_isBlue, false);

        } finally {
            a.recycle();
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint();
        if (isBlue) {
            p.setColor(0xFF0000FF);
        } else {
            p.setColor(0xFFFF0000);
        }

        canvas.drawLine(0, 0, getMeasuredWidth(), getMeasuredHeight(), p);

        if (isDown) {
            p.setColor(0xFF00FF00);
            //Convert px to dp
            float px = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    5,
                    getContext().getResources().getDisplayMetrics());
            p.setStrokeWidth(px);
            canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(), 0, p);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDown = true;
                invalidate(); //ลบแล้ววาดใหม่
                return true;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                isDown = false;
                invalidate();
                return true;
            case MotionEvent.ACTION_CANCEL:
                isDown = false;
                invalidate();
                return true;

        }
        return super.onTouchEvent(event);
    }
}
