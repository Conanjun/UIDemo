package com.example.conan.uidemo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CustomWidgetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_widget);
        //隐藏系统自带的标题栏
        ActionBar actionBar=getSupportActionBar();
        if(actionBar !=null){
            actionBar.hide();
        }
    }
}
