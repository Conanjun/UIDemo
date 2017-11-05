package com.example.conan.uidemo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class CommonWidgetsActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "CommonWidgetsActivity";

    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;
    private ProgressDialog progressDialog;

    private int image_index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_widgets);
        //初始化控件
        imageView=(ImageView) findViewById(R.id.image_view);
        editText=(EditText)findViewById(R.id.edit_text1);
        progressBar=(ProgressBar)findViewById(R.id.progress_bar);
        progressDialog=new ProgressDialog(CommonWidgetsActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                Toast.makeText(CommonWidgetsActivity.this,"Your clicked button1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                Toast.makeText(CommonWidgetsActivity.this,editText.getText().toString(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                //if(imageView.)
                int [] images=new int[]{
                        R.drawable.img_1,
                        R.drawable.img_2
                };
                imageView.setImageResource(images[(image_index++)%images.length]);
                break;
            case R.id.button4:
                int progress=progressBar.getProgress();
                if(progress<100){
                    progress=progress+10;
                    progressBar.setProgress(progress);
                }else{
                    AlertDialog.Builder dialog=new AlertDialog.Builder(CommonWidgetsActivity.this);
                    dialog.setTitle("This is Dialog");
                    dialog.setMessage("Progress is finished,if you clicked again,it will start from 0.");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            progressBar.setProgress(0);
                        }
                    });
                    dialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    dialog.show();
                }
                break;
            case R.id.button5:
                progressDialog.setTitle("This is ProgressDialog");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.setMax(100);
                progressDialog.show();
                //开启新线程执行任务并更新progress
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        updateProgress();
                        handler.sendEmptyMessage(0);
                    }
                }).start();
                break;
            default:
                break;
        }
    }

    private void updateProgress(){
        try{
            int progress=progressDialog.getProgress();
            while(progress<100){
                progress=progress+10;
                progressDialog.setProgress(progress);
                Log.d(TAG,"progressing");
                Thread.sleep(500);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //关闭progressDialog
            progressDialog.dismiss();
            Toast.makeText(CommonWidgetsActivity.this,"task finished",Toast.LENGTH_SHORT).show();
        }
    };
}
