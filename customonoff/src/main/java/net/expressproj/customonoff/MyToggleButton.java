package net.expressproj.customonoff;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * Created by adventurer on 16-9-10.
 */
public class MyToggleButton extends View implements View.OnClickListener{

    private Bitmap backGroundBItmap;
    private Bitmap slidingBitmap;
    private int slidLeftMax;
    private Paint paint;
    private boolean isOpen = false;

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
        if(isOpen){
            canvas.drawBitmap(slidingBitmap,slidLeftMax,0,paint);
        } else {
            canvas.drawBitmap(slidingBitmap,0,0,paint);
        }
    }


    @Override
    public void onClick(View view) {
        Toast.makeText(getContext(),"被点击了",Toast.LENGTH_SHORT).show();
        isOpen = !isOpen;
        invalidate();
    }
}
