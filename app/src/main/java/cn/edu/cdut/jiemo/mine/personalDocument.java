package cn.edu.cdut.jiemo.mine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.zhy.changeskin.SkinManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import cn.edu.cdut.jiemo.R;
import cn.edu.cdut.jiemo.fragment.titleFragment;

/**
 * Created by aaa on 2019/12/1.
 */

public class personalDocument extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册换肤功能
        SkinManager.getInstance().register(this);

        setContentView(R.layout.activity_personal_document);

        //去掉系统自带的标题栏
        ActionBar actionbar=getSupportActionBar();
        if(actionbar !=null)
        {
            actionbar.hide();
        }

//        //设置标题栏内容
//        titleFragment titlefragment=(titleFragment)getSupportFragmentManager().findFragmentById(R.id.title_fragement);
//        titlefragment.setTitle("个人资料");

        //获取年龄、性别等控件
        personalItemActivity headculpture=findViewById(R.id.headculpture);
        personalItemActivity  userName=findViewById(R.id.userName);
        personalItemActivity sex=findViewById(R.id.sex);
        personalItemActivity age=findViewById(R.id.age);
        personalItemActivity personalizedSignature=findViewById(R.id.personalizedSignature);

        //获取用户的信息
        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        String name=pref.getString("name","");
        String ageinf=Integer.toString(pref.getInt("age",0));
        String sexinf=pref.getString("sex","男");
        String words=pref.getString("words","这个人很懒");

        //注入年龄、性别等数据
        userName.setRightText(name);
        sex.setRightText(sexinf);
        age.setRightText(ageinf);
        personalizedSignature.setRightText(words);

        //将左边的图标大小设置为0
        headculpture.setLeftIconSize(0);
        userName.setLeftIconSize(0);
        sex.setLeftIconSize(0);
        age.setLeftIconSize(0);
        personalizedSignature.setLeftIconSize(0);

        //跳转到编辑界面
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(personalDocument.this, documentEditer.class);
                intent.putExtra("titleText","昵称");
                intent.putExtra("dataKey","name");
                startActivity(intent);
            }
        });

        sex.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(personalDocument.this,documentEditer.class);
                intent.putExtra("titleText","性别");
                intent.putExtra("dataKey","sex");
                startActivity(intent);
            }
        });


        age.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(personalDocument.this,documentEditer.class);
                intent.putExtra("titleText","年龄");
                intent.putExtra("dataKey","age");
                startActivity(intent);
            }
        });

        personalizedSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(personalDocument.this,documentEditer.class);
                intent.putExtra("titleText","个性签名");
                intent.putExtra("dataKey","words");
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //获取年龄、性别等控件
        personalItemActivity headculpture=findViewById(R.id.headculpture);
        personalItemActivity  userName=findViewById(R.id.userName);
        personalItemActivity sex=findViewById(R.id.sex);
        personalItemActivity age=findViewById(R.id.age);
        personalItemActivity personalizedSignature=findViewById(R.id.personalizedSignature);
        //获取用户的信息
        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        String name=pref.getString("name","");
        String ageinf=Integer.toString(pref.getInt("age",0));
        String sexinf=pref.getString("sex","男");
        String words=pref.getString("words","这个人很懒");

        //注入年龄、性别等数据
        userName.setRightText(name);
        sex.setRightText(sexinf);
        age.setRightText(ageinf);
        personalizedSignature.setRightText(words);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //换肤功能注销
        SkinManager.getInstance().unregister(this);
    }
}
