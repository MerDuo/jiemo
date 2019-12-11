package cn.edu.cdut.jiemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import cn.edu.cdut.jiemo.diary.SectionsPagerAdapter;
import cn.edu.cdut.jiemo.mine.mine;
import cn.edu.cdut.jiemo.schedule.addplan;
import cn.edu.cdut.jiemo.schedule.calender;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    private ScrollView scrollView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        //滚动效果
//        scrollView = (ScrollView) findViewById(R.id.id_scrollView);
//        scrollView.setVerticalScrollBarEnabled(false);
//        scrollView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_UP:
//                        // 判断是否到达顶部
//                        if (scrollView.getScrollY() <= 0) {
////                            Toast.makeText(MainActivity.this, "到达顶部", Toast.LENGTH_SHORT).show();
//                            break;
//                        }
//                        // 判断是否到达底部
//                        else if (scrollView.getChildAt(0).getMeasuredHeight() <=
//                                scrollView.getHeight() + scrollView.getScrollY()) {
//                            Toast.makeText(MainActivity.this, "到达底部", Toast.LENGTH_SHORT).show();
//                            break;
//                        }
//                        break;
//                }
//                return false;
//            }
//        });


        // 菜单栏
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this,getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        // 添加日志点击事件
        //ImageView addDiary = (ImageView) findViewById(R.id.jia);
        //addDiary.setOnClickListener(this);

//        // 添加日程
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent(calender.this,addplan.class);
//                Intent intent = new Intent(MainActivity.this, addplan.class);
//                startActivity(intent);
//            }
//        });


    }

    //    点击加号进入跳转界面
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.jia:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, add.class);
                startActivity(intent);
                break;
            case R.id.wo:
                Intent intent1 = new Intent();
                intent1.setClass(MainActivity.this,mine.class);
                startActivity(intent1);
                break;
            case R.id.fab:
                Intent intent2 = new Intent(MainActivity.this,addplan.class);
//                Intent intent = new Intent(MainActivity.this, addplan.class);
                startActivity(intent2);
        }
    }

}






