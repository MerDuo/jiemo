package cn.edu.cdut.jiemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;

import com.google.android.material.tabs.TabLayout;
import com.zhy.changeskin.SkinManager;

import cn.edu.cdut.jiemo.diary.SectionsPagerAdapter;
import cn.edu.cdut.jiemo.mine.mine;
import cn.edu.cdut.jiemo.schedule.addplan;
import cn.edu.cdut.jiemo.mine.inputSecuritypwd;
import cn.edu.cdut.jiemo.schedule.sqLite;
import cn.edu.cdut.jiemo.userBean.userBean;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    SharedPreferences securityState;
    Boolean state;
    Boolean isOK;
    private ScrollView scrollView = null;

    private sqLite mySQLiteOpenHelper;
    userBean user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册换肤功能
        SkinManager.getInstance().register(this);
        setContentView(R.layout.activity_main);

            String uname = "test";
            String psw = "123123";
            mySQLiteOpenHelper = new sqLite(this);
            userBean user = new userBean();
            user.userName = uname;
            user.password = psw;
            mySQLiteOpenHelper.addUser(user);

        securityState=getSharedPreferences("securityState",MODE_PRIVATE);
        state=securityState.getBoolean("securityState",false);
        isOK=securityState.getBoolean("isOK",false);

        if(state==true && isOK==false){
            Intent intent = new Intent(MainActivity.this, inputSecuritypwd.class);
            startActivity(intent);
        }
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

        // 跳转指定viewpager
        Intent intent = getIntent();
        int page = intent.getIntExtra("page",0);
//        sectionsPagerAdapter.getItem(page);
        viewPager.setCurrentItem(page);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        //
//        PopupMenu popupMenu = new PopupMenu(this,v);
//        popupMenu.setOnMenuItemClickListener(this);
//        popupMenu.inflate(R.menu.menu_diary);
//        popupMenu.show();

//        public boolean onCreateOptionsMenu(Menu menu) {
//            // Inflate the menu; this adds items to the action bar if it is present.
//            getMenuInflater().inflate(R.menu.menu_main, menu);
//            return true;
//        }
//
//        public boolean onOptionsItemSelected(MenuItem item) {
//            // Handle action bar item clicks here. The action bar will
//            // automatically handle clicks on the Home/Up button, so long
//            // as you specify a parent activity in AndroidManifest.xml.
//            int id = item.getItemId();
//
//            //noinspection SimplifiableIfStatement
//            if (id == R.id.action_settings) {
//                return true;
//            }
//
//            return super.onOptionsItemSelected(item);
//        }

        // 添加日志点击事件
        //ImageView addDiary = (ImageView) findViewById(R.id.jia);
        //addDiary.setOnClickListener(this);

        // 添加日程
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent(calender.this,addplan.class);
//                Intent intent = new Intent(MainActivity.this, addplan.class);
//                startActivity(intent);
//            }
//        });

        //将是否验证成功设为否
        SharedPreferences.Editor editor2=securityState.edit();
        editor2.putBoolean("isOK",false);
        editor2.commit();
    }

    //    点击加号进入跳转界面
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.sy:
                Intent intent3 = new Intent();
                intent3.setClass(MainActivity.this,MainActivity.class);
                startActivity(intent3);
                break;
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
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //换肤功能注销
        SkinManager.getInstance().unregister(this);
    }

}






