package com.hudson.mindfill;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by kaylab on 4/2/16.
 */
public class SquareRelativeLayout extends RelativeLayout {


    public SquareRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
