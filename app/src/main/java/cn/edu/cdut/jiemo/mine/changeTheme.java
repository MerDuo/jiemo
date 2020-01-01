package cn.edu.cdut.jiemo.mine;

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
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册换肤功能
        SkinManager.getInstance().register(this);
        setContentView(R.layout.changet_theme);

        //控件初始化
        b1=findViewById(R.id.defaultTheme);
        b2=findViewById(R.id.white);
        b3=findViewById(R.id.purple);
        b4=findViewById(R.id.blue);

        //设置监听事件更改主题
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().changeSkin("");
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().changeSkin("4");
                getUser().changeTheme("4");
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().changeSkin("3");
                getUser().changeTheme("3");
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().changeSkin("2");
                getUser().changeTheme("2");
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
