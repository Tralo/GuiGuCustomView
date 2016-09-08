package com.example.propertyanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_animation;
    private TextView tv_animation_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        iv_animation = $(R.id.iv_animation);
        iv_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击了图片",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private <T extends View> T $(int viewId){
        return (T) findViewById(viewId);
    }

    /**
     * 补间(视图)动画
     * @param v
     */
    public void testTweenAnimation(View v) {
        TranslateAnimation animation = new TranslateAnimation(0, iv_animation.getWidth(), 0, iv_animation.getHeight());
        animation.setDuration(3000);
        animation.setFillAfter(true);
        iv_animation.startAnimation(animation);
    }


    private AnimatorSet animatorSet;
    /**
     * testPropertyAnimation
     * @param v
     */
    public void testPropertyAnimation(View v) {

//        v.setTranslationY();

        ObjectAnimator animator3 = ObjectAnimator.ofFloat(iv_animation,"translationX",0,iv_animation.getWidth());
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(iv_animation,"translationY",0,iv_animation.getHeight());

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator3,animator4);
        set.setDuration(2000);
        set.start();

    }

    public void reset(View v) {
        iv_animation.clearAnimation();
    }
}
