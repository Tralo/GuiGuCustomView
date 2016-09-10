package com.example.youku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_home;
    private ImageView iv_menu;
    private RelativeLayout rl_level1;
    private RelativeLayout rl_level2;
    private RelativeLayout rl_level3;

    /**
     * 是否显示第三圆环
     */
    private boolean isShowLevel3 = true;
    /**
     * 是否显示第二圆环
     */
    private boolean isShowLevel2 = true;
    /**
     * 是否显示第一圆环
     */
    private boolean isShowLevel1 = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        iv_home.setOnClickListener(this);
        iv_menu.setOnClickListener(this);
        rl_level1.setOnClickListener(this);
        rl_level2.setOnClickListener(this);
        rl_level3.setOnClickListener(this);

    }

    private void initView() {
        iv_home = $(R.id.icon_home);
        iv_menu = $(R.id.icon_menu);
        rl_level1 = $(R.id.level1);
        rl_level2 = $(R.id.level2);
        rl_level3 = $(R.id.level3);
    }


    private <T extends View> T $(int viewId){
        return (T) findViewById(viewId);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId){
            case R.id.level1:
                Toast.makeText(MainActivity.this,"level1被点击老",Toast.LENGTH_SHORT).show();
                break;
            case R.id.level2:
                Toast.makeText(MainActivity.this,"level2被点击老",Toast.LENGTH_SHORT).show();
                break;
            case R.id.level3:
                Toast.makeText(MainActivity.this,"level2被点击老",Toast.LENGTH_SHORT).show();
                break;
            case R.id.icon_home:
                if(isShowLevel2){
                    isShowLevel2 = false;
                    if(isShowLevel3){
                        isShowLevel3 = false;
                        Tools.hideView(rl_level3,550);
                    }
                    Tools.hideView(rl_level2);
                } else {
                    isShowLevel2 = true;
                    Tools.showView(rl_level2);
                }
                break;
            case R.id.icon_menu:
                if(isShowLevel3){
                    isShowLevel3 = false;
                    Tools.hideView(rl_level3);
                } else {
                    isShowLevel3 = true;
                    Tools.showView(rl_level3);
                }

                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_MENU){
            if(isShowLevel1){
                isShowLevel1 = false;
                Tools.hideView(rl_level1);

                if(isShowLevel2){
                    isShowLevel2 = false;
                    Tools.hideView(rl_level2,400);
                    if(isShowLevel3){
                        isShowLevel3 = false;
                        Tools.hideView(rl_level2,800);
                    }

                }

            } else {
                isShowLevel1 = true;
                Tools.showView(rl_level1);


                isShowLevel2 = true;
                Tools.showView(rl_level2,400);
            }

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
