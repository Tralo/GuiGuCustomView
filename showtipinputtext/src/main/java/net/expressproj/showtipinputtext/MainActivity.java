package net.expressproj.showtipinputtext;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText et_input;
    private ImageView iv_down_arrow;
    private PopupWindow popupWindow;

    private ListView listView;
    private ArrayList<String> messages;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initData() {

        messages = new ArrayList<>();
        for(int i = 0; i < 500; i++){
            messages.add(i + "--aaaaaaaaa--" + i);
        }
        adapter = new MyAdapter();
        listView.setAdapter(adapter);

    }

    private void initListener() {
        et_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(popupWindow == null){
                    popupWindow = new PopupWindow(listView);
                    popupWindow.setWidth(et_input.getWidth());
                    popupWindow.setHeight(DensityUtil.dip2px(MainActivity.this,200));//dp-px
                    popupWindow.setContentView(listView);
                    popupWindow.setFocusable(true);//设置焦点

                }
                popupWindow.showAsDropDown(et_input,0,0);
            }
        });
    }



    private void initView() {
        et_input = $(R.id.et_input);
        iv_down_arrow = $(R.id.iv_down_arrow);
        listView = new ListView(this);
/*
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        listView.setLayoutParams(params);
*/

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String msg = messages.get(i);

                et_input.setText(msg);

                if(popupWindow != null && popupWindow.isShowing()){
                    popupWindow.dismiss();
                }

            }
        });
        listView.setBackgroundResource(R.drawable.listview_background);

    }

    private <T extends View> T $(int viewId){
        return (T) findViewById(viewId);
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return messages.size();
        }

        @Override
        public Object getItem(int i) {
            return messages.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if(view == null){
                view = View.inflate(MainActivity.this,R.layout.item_main,null);
                viewHolder = new ViewHolder();
                viewHolder.tv_msg = (TextView) view.findViewById(R.id.tv_msg);
                viewHolder.iv_delete = (ImageView) view.findViewById(R.id.iv_delete);
                view.setTag(viewHolder);
            } else{
                viewHolder = (ViewHolder) view.getTag();
            }

            final String msg = messages.get(i);
            viewHolder.tv_msg.setText(msg);

            viewHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //1. 从集合中删除

                    messages.remove(i);
                    //2.刷新适配器
                    adapter.notifyDataSetChanged();//getCount() --> getView()
                }
            });



            return view;
        }
    }
    static class ViewHolder{
        TextView tv_msg;
        ImageView iv_delete;
    }

}
