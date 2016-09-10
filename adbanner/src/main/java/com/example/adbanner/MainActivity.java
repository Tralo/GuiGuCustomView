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
    private int prePosition = 0;


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

        //设置中间位置
        int item = Integer.MAX_VALUE/2 - Integer.MAX_VALUE/2%imageViews.size();//要保证imageViews的整数倍
        vp_banner.setCurrentItem(item);
    }

    private void initAdapter() {

        vp_banner.setAdapter(new MyPagerAdapter());
        vp_banner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int realPosition = position % imageIds.length;

                tv_title.setText(titles[realPosition]);
                ll_point_group.getChildAt(prePosition).setEnabled(false);
                ll_point_group.getChildAt(realPosition).setEnabled(true);
                prePosition = realPosition;
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
            int realPosition = position % imageIds.length;

            ImageView iv = imageViews.get(realPosition);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
