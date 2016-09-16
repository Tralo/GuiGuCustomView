package net.expressproj.followviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by adventurer on 16-9-13.
 */
public class MyViewPager extends ViewGroup {

    private MyScroller scroller;

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
        scroller = new MyScroller();
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
    private void scrollToPager(int tempIndex) {
        if(tempIndex < 0){
            tempIndex = 0;
        } else if(tempIndex > getChildCount() - 1){
            tempIndex = getChildCount() - 1;
        }
        //当前页面的下标位置
        currentIndex = tempIndex;
        float distanceX = currentIndex * getWidth() - getScrollX();
//        scrollTo(getWidth() * currentIndex,getScrollY());

        scroller.startScroll(getScrollX(),getScaleY(),distanceX,0);

        invalidate();
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
}
