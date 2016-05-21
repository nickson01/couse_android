package com.walkingwithdev.nick.helloworld;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Nick on 5/20/2016 AD.
 */
public class CustomView extends View {
    private boolean isBlue = false;
    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initWithAtts(attrs,0,0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initWithAtts(attrs,defStyleAttr,0);
    }

    @TargetApi(21)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
        initWithAtts(attrs,defStyleAttr,defStyleRes);
    }

    private void init() {

    }
    private void  initWithAtts(AttributeSet attrs,int defStyleAttr,int defStyleRes){
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomView,
                defStyleAttr,
                defStyleRes);
        try {
            isBlue = a.getBoolean(R.styleable.CustomView_isBlue,false);
        }finally {
            a.recycle();
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint();
        if (isBlue){
            p.setColor(0xFF0000FF);
        }else {
            p.setColor(0xFFFF0000);
        }

        canvas.drawLine(0,0,getMeasuredWidth(),getMeasuredHeight(),p);

    }
}
