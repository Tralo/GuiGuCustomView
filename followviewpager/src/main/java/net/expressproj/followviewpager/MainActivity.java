package net.expressproj.followviewpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private MyViewPager mvp;
    private RadioGroup rg_main;
    private int[] ids = {
            R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4,R.drawable.a5,R.drawable.a6

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mvp = (MyViewPager) findViewById(R.id.mvp);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        //添加页面
        for(int i= 0;i  < ids.length;i++){
            ImageView iv =new ImageView(this);
            iv.setBackgroundResource(ids[i]);
            //添加到MyViewPager中
            mvp.addView(iv);
        }

        for(int i = 0;i < mvp.getChildCount(); i++){
            RadioButton button = new RadioButton(this);
            button.setId(i);//0 ~
            if(i == 0){
                button.setChecked(true);
            }
            rg_main.addView(button);
        }
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             *
             * @param radioGroup
             * @param id
             */
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                mvp.scrollToPager(id);
            }
        });

        mvp.setOnPageChangeListener(new MyViewPager.OnPageChangeListener() {
            @Override
            public void onScrollToListener(int position) {
               rg_main.check(position);
            }
        });

    }
}
