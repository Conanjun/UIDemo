package com.example.conan.uidemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BasicLayoutActivity extends AppCompatActivity {
    private static final String TAG = "BasicLayoutActivity";

    private String[] basiclayout_types={
            "LinearLayout",
            "RelativeLayout",
            "FrameLayout",
            "PercentFrameLayout"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_layout);
        ArrayAdapter<String > adapter=new ArrayAdapter<String>(BasicLayoutActivity.this,android.R.layout.simple_list_item_1,basiclayout_types);
        ListView listView=(ListView) findViewById(R.id.list_view_basic_layout);
        listView.setAdapter(adapter);
        //为listView添加点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String ui=basiclayout_types[position];
                //Toast.makeText(MainActivity.this,ui,Toast.LENGTH_SHORT).show();
                //根据UI type启动不同的activity,这里使用反射(用字符串作为类名获取一个class传入intent)
                String activityClassName=getPackageName()+".basiclayoutactivities."+ui;
                Log.d(TAG,activityClassName);
                try {
                    Class<?> activityClass = Class.forName(activityClassName);
                    Intent intent=new Intent(BasicLayoutActivity.this,activityClass);
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
