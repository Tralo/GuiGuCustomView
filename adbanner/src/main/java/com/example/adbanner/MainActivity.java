package com.example.adbanner;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp_banner;
    private LinearLayout ll_point_group;
    private TextView tv_title;


    private List<ImageView> imageViews = new ArrayList<ImageView>();

    private int[] imageIds = {
        R.mipmap.a,R.mipmap.b,R.mipmap.c,R.mipmap.d,R.mipmap.e
    };

    private String[] titles = {
        "钢铁侠","美国队长","东京史诗贵","复仇者联盟","鼠会"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initChildPage();
        initAdapter();

    }

    private void initAdapter() {

        vp_banner.setAdapter(new MyPagerAdapter());
        vp_banner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                vp_banner.setCurrentItem(position);
                tv_title.setText(titles[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initChildPage() {
        for(int i = 0; i < imageIds.length; i++){
            ImageView iv = new ImageView(this);
            iv.setBackgroundResource(imageIds[i]);

            imageViews.add(iv);

            ImageView iv_point = new ImageView(this);
            iv_point.setBackgroundResource(R.drawable.point_selector);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(8,8);
            iv_point.setLayoutParams(params);
            if(i == 0){
                iv_point.setEnabled(true);
            } else {
                iv_point.setEnabled(false);
                params.leftMargin = 8;
            }


            ll_point_group.addView(iv_point);

        }

    }

    private void initView() {

        vp_banner = $(R.id.vp_banner);
        ll_point_group = $(R.id.ll_point_group);
        tv_title = $(R.id.tv_title);
        tv_title.setText(titles[0]);
    }

    private <T extends View> T $(int viewId){
        return (T) findViewById(viewId);
    }

    class MyPagerAdapter extends PagerAdapter{
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = imageViews.get(position);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews.get(position));
        }

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
