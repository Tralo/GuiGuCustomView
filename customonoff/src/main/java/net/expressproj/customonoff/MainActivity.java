package net.expressproj.customonoff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 *  一个视图从创建到显示过程中的主要方法
 *  1.构造方法实例化类
 *  2.测量measure(int,int) --> onMeasure();
 *  如果当前View是一个ViewGroup,还有义务测量孩子
 *  3.测量位置-layout()-->onLayout();
 *  指定控件的位置,一般View 不用写这个方法，ViewGroup的时候才需要，一般View不需要重写该方法
 *  4.绘制视图--draw()-->onDraw(canvas)
 *  根据上面两个方法参数，进入绘制
 */
public class MainActivity extends AppCompatActivity {

    private MyToggleButton tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tb = (MyToggleButton) findViewById(R.id.tb);

    }
}
