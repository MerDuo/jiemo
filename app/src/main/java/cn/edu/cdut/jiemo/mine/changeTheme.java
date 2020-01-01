package cn.edu.cdut.jiemo.mine;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zhy.changeskin.SkinManager;

import cn.edu.cdut.jiemo.R;

import static cn.edu.cdut.jiemo.mine.mineUserDao.getUser;


/**
 * Created by aaa on 2019/12/28.
 */

public class changeTheme extends AppCompatActivity {
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Boolean isLogin;
    SharedPreferences pref;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册换肤功能
        SkinManager.getInstance().register(this);
        setContentView(R.layout.changet_theme);

        //初始化SharedPreferences
        pref=getSharedPreferences("loginInfo",MODE_PRIVATE);


        //控件初始化
        b1=findViewById(R.id.defaultTheme);
        b2=findViewById(R.id.pink);
        b3=findViewById(R.id.purple);
        b4=findViewById(R.id.blue);

        //设置监听事件更改主题
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().changeSkin("");
                //如果登录则改变用户的theme数据
                isLogin=pref.getBoolean("isLogin",false);
                if(isLogin){
                    getUser().changeTheme("");
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().changeSkin("4");
                isLogin=pref.getBoolean("isLogin",false);
                if(isLogin){
                    getUser().changeTheme("4");
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().changeSkin("3");
                isLogin=pref.getBoolean("isLogin",false);
                if(isLogin){
                    getUser().changeTheme("3");
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().changeSkin("2");
                isLogin=pref.getBoolean("isLogin",false);
                if(isLogin){
                getUser().changeTheme("2");
                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //换肤功能注销
        SkinManager.getInstance().unregister(this);
    }
}
