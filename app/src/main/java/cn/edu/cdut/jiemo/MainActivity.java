package cn.edu.cdut.jiemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity{

    private ScrollView scrollView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        findViewById(R.id.time).setOnClickListener((View.OnClickListener) this);

        //滚动效果
        scrollView = (ScrollView) findViewById(R.id.id_scrollView);
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        // 判断是否到达顶部
                        if (scrollView.getScrollY() <= 0) {
//                            Toast.makeText(MainActivity.this, "到达顶部", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        // 判断是否到达底部
                        else if (scrollView.getChildAt(0).getMeasuredHeight() <=
                                scrollView.getHeight() + scrollView.getScrollY()) {
                            Toast.makeText(MainActivity.this, "到达底部", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        break;
                }
                return false;
            }
        });


        ImageView imageView=findViewById(R.id.jia);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,add.class);
//                startActivity(intent);
//            }
//        });
    }
}






