package com.example.youku;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by adventurer on 16-9-8.
 */
public class Tools {
    public static void hideView(View view) {
        hideView(view,0);
    }

    public static void showView(View view) {
        showView(view,0);
    }

    public static void hideView(View view, int startOffset) {
        RotateAnimation ra = new RotateAnimation(0,180,view.getWidth() / 2, view.getHeight());
        ra.setDuration(500);
        ra.setFillAfter(true);
        ra.setStartOffset(startOffset);//延迟多久后在
        view.startAnimation(ra);
    }

    public static void showView(View view, int startOffset) {
        RotateAnimation ra = new RotateAnimation(180,360,view.getWidth() / 2, view.getHeight());
        ra.setDuration(500);
        ra.setFillAfter(true);
        ra.setStartOffset(startOffset);//延迟多久后在
        view.startAnimation(ra);
    }
}
