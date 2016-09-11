package net.expressproj.customonoff;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by adventurer on 16-9-10.
 */
public class MyToggleButton extends View implements View.OnClickListener{

    private Bitmap backGroundBItmap;
    private Bitmap slidingBitmap;
    private int slidLeftMax;
    private float slidLeft;
    private Paint paint;
    private boolean isOpen = false;
    private boolean isEnableClick = true;

    /**
     * 如果我们在布局文件使用该类，将会用这个构造方法使用该类，如果没有就会崩溃
     * @param context
     * @param attrs
     */
    public MyToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setAntiAlias(true);//设置抗锯齿
        backGroundBItmap = BitmapFactory.decodeResource(getResources(),R.mipmap.switch_background);
        slidingBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.slide_button);
        slidLeftMax = backGroundBItmap.getWidth() - slidingBitmap.getWidth();
        setOnClickListener(this);
    }

    /**
     * 测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(backGroundBItmap.getWidth(),backGroundBItmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.drawBitmap(backGroundBItmap,0,0,paint);
        canvas.drawBitmap(slidingBitmap,slidLeft,0,paint);
    }


    @Override
    public void onClick(View view) {
        Toast.makeText(getContext(),"被点击了",Toast.LENGTH_SHORT).show();
        if(isEnableClick){
            isOpen = !isOpen;
            flushView();
        }

    }

    private float startX = 0;
    private float lastX = 0;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = event.getX();
                startX = event.getX();
                isEnableClick = true;
                break;
            case MotionEvent.ACTION_MOVE:
                isEnableClick = false;
                float endX = event.getX();
                float distance = endX - startX;
                slidLeft += distance;
                if(slidLeft < 0){
                    slidLeft = 0;
                }
                if(slidLeft > slidLeftMax){
                    slidLeft = slidLeftMax;
                }
                startX = event.getX();

                invalidate();
                if(Math.abs(endX - lastX) > 5){
                    isEnableClick = false;

                }

                break;
            case MotionEvent.ACTION_UP:
               if(!isEnableClick){
                   if(slidLeft > slidLeftMax / 2){
                       isOpen = true;

                   } else {

                       isOpen = false;
                   }
                   isEnableClick = false;
                   flushView();
               }


                break;

        }

        return true;
    }

    private void flushView() {
        if(isOpen){
            slidLeft = slidLeftMax;
        } else {
            slidLeft = 0;
        }
        invalidate();
    }
}
