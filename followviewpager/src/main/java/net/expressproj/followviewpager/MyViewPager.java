package net.expressproj.followviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.Toast;

/**
 * Created by adventurer on 16-9-13.
 */
public class MyViewPager extends ViewGroup {

    private Scroller scroller;

    private OnPageChangeListener mListener;


    public void setOnPageChangeListener(OnPageChangeListener l) {
        this.mListener = l;
    }

    /**
     * 手势识别器
     * 1.定义出来
     * 2.实例化-把想要的方法给复写
     * 3.在onTouchEvent()把事件传递给手势识别器
     */

    private GestureDetector detector;

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(final Context context) {
        scroller = new Scroller(context);
        detector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
                Toast.makeText(context,"长按",Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                scrollBy((int)distanceX,0);
                return true;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Toast.makeText(context,"双击",Toast.LENGTH_SHORT).show();
                return super.onDoubleTap(e);
            }
        });


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        //遍历孩子，给每个孩子设置坐标
        for(int i = 0; i < getChildCount(); i++){
            View childView = getChildAt(i);
            childView.layout(getWidth() * i,0,(i+1) * getWidth(),getHeight());
        }
    }



    private float downX;
    private float downY;

    /**
     * 如果当前方法，返回ture,拦截事件，将会触发当前控件的onTouchEvent()方法
     * 如果当前方法,返回false,不拦截事件，事件继续传递给孩子
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        detector.onTouchEvent(ev);
        boolean result = false;//默认传递给孩子

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                System.out.println("onInterceptTouchEvent==ACTION_DOWN");
                //1.记录坐标
                downX = ev.getX();
                downY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("onInterceptTouchEvent==ACTION_MOVE");
                //2.记录结束值
                float endX = ev.getX();
                float endY = ev.getY();

                //3.计算绝对值
                float distanceX = Math.abs(endX - downX);
                float distanceY = Math.abs(endY - downY);

                if(distanceX > distanceY&&  distanceX >5){
                    result = true;
                }else{
                    scrollToPager(currentIndex);
                }

                break;
            case MotionEvent.ACTION_UP:
                System.out.println("onInterceptTouchEvent==ACTION_UP");

                break;
        }
        return result;
    }

    private float startX;
    private int currentIndex = 0;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        //3.把事件传递给手势识别器
        detector.onTouchEvent(event);

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //1.记录坐标
                startX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:


                break;
            case MotionEvent.ACTION_UP:
                //2.来到新的坐标
                float endX = event.getX();

                int tempIndex = currentIndex;
                if((startX - endX) > getWidth() / 2){
                    tempIndex++;
                } else if((endX - startX) > getWidth() / 2){
                    tempIndex--;
                }
                //根据下标位置移动到指定的页面
                scrollToPager(tempIndex);

                break;
        }

        return true;
    }

    /**
     * 屏蔽非法值
     * @param tempIndex
     */
    public void scrollToPager(int tempIndex) {
        if(tempIndex < 0){
            tempIndex = 0;
        }

        if(tempIndex > getChildCount()-1){
            tempIndex = getChildCount()-1;
        }
        //当前页面的下标位置
        currentIndex = tempIndex;


        if(mListener != null){
//            mOnPagerChangListenter ==MyOnPagerChangListenter;
            //MyOnPagerChangListenter 里面有一个方法，onScrollToPager(int)
            Log.e("yangguangfu", "MyViewPager--scrollToPager____mOnPagerChangListenter=="+mListener);
            mListener.onScrollToListener(currentIndex);
        }

        //总距离计算出来
        int distanceX = currentIndex*getWidth() - getScrollX();
        // int distanceX = 目标 - getScrollX();
        //移动到指定的位置
//        scrollTo(currentIndex*getWidth(),getScrollY());
//        scroller.startScroll(getScrollX(),getScrollY(),distanceX,0);

        scroller.startScroll(getScrollX(),getScrollY(),distanceX,0,Math.abs(distanceX));

        //
        invalidate();//强制绘制;//onDraw();computeScroll();
    }

    @Override
    public void computeScroll() {
//        super.computeScroll();
        while(scroller.computeScrollOffset()){
            float currX = scroller.getCurrX();
            scrollTo((int)currX,0);
            invalidate();
        }
    }


    /**
     * 监听页面的改变
     */
    public interface OnPageChangeListener{
        /**
         * 当页面改变的时候回调
         * @param position
         */
        void onScrollToListener(int position);
    }

}
