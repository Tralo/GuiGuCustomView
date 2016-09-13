package net.expressproj.followviewpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private MyViewPager mvp;
    private int[] ids = {
            R.mipmap.a1,R.mipmap.a2,R.mipmap.a3,R.mipmap.a4,R.mipmap.a5,R.mipmap.a6

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mvp = (MyViewPager) findViewById(R.id.mvp);

        //添加页面
        for(int i= 0;i  < ids.length;i++){
            ImageView iv =new ImageView(this);
            iv.setBackgroundResource(ids[i]);
            //添加到MyViewPager中
            mvp.addView(iv);
        }

    }
}
