package net.expressproj.customattr;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by adventurer on 16-9-12.
 */
public class MyAttributeView extends View {

    private static final String TAG = "MyAttributeView";

    private int my_age;
    private String my_name;
    private Bitmap my_bg;
    private Paint paint;


    public MyAttributeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        //获取属性三种方式
        //1.用命名控件获取
        String age = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","my_age");
        String name = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","my_name");
        String bg = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","my_bg");
        Log.i(TAG,"age  = " + age + ", name = " + name + ", bg = " + bg);

        //2.遍历属性集合
        for(int i = 0; i < attrs.getAttributeCount(); i++){
            Log.i(TAG,attrs.getAttributeName(i) + "=====" + attrs.getAttributeValue(i));

        }
        //3.使用系统工具，获取属性
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.MyAttributeView);
        for(int i = 0; i < ta.getIndexCount(); i++){
            int index = ta.getIndex(i);
            switch (index){
                case R.styleable.MyAttributeView_my_age:
                    my_age = ta.getInt(index,0);
                    break;
                case R.styleable.MyAttributeView_my_name:
                    my_name = ta.getString(index);
                    break;
                case R.styleable.MyAttributeView_my_bg:
                    Drawable drawable = ta.getDrawable(index);
                    BitmapDrawable bd = (BitmapDrawable) drawable;
                    my_bg = bd.getBitmap();
                    break;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(my_name +" --- "+ my_age,50,50,paint);
        canvas.drawBitmap(my_bg,0,50,paint);
    }
}
