package com.example.youku;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by adventurer on 16-9-8.
 */
public class Tools {
    public static void hideView(ViewGroup view) {
        hideView(view,0);
    }

    public static void showView(ViewGroup view) {
        showView(view,0);
    }

    public static void hideView(ViewGroup view, int startOffset) {
        RotateAnimation ra = new RotateAnimation(0,180,view.getWidth() / 2, view.getHeight());
        ra.setDuration(500);
        ra.setFillAfter(true);
        ra.setStartOffset(startOffset);//延迟多久后在
        view.startAnimation(ra);
        for(int i = 0; i < view.getChildCount(); i++){
            View childView = view.getChildAt(i);
            childView.setEnabled(false);
        }
    }

    public static void showView(ViewGroup view, int startOffset) {
        RotateAnimation ra = new RotateAnimation(180,360,view.getWidth() / 2, view.getHeight());
        ra.setDuration(500);
        ra.setFillAfter(true);
        ra.setStartOffset(startOffset);//延迟多久后在
        view.startAnimation(ra);
        for(int i = 0; i < view.getChildCount(); i++){
            View childView = view.getChildAt(i);
            childView.setEnabled(true);
        }
    }
}
