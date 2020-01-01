package cn.edu.cdut.jiemo.mine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zhy.changeskin.SkinManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import cn.edu.cdut.jiemo.R;

import static cn.edu.cdut.jiemo.mine.mineUserDao.getUser;

/**
 * Created by aaa on 2019/12/1.
 */

public class personalDocument extends AppCompatActivity {
    //年龄、性别等控件
    personalItemActivity headculpture;
    personalItemActivity  userName;
    personalItemActivity sex;
    personalItemActivity age;
    personalItemActivity personalizedSignature;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置界面
        setLayout();
        //设置点击事件
        setClickListeners();

    }
    @Override
    protected void onStart() {
        super.onStart();
        //注入用户数据
        refreash();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //换肤功能注销
        SkinManager.getInstance().unregister(this);
    }

    //界面相关操作
    public void setLayout(){
        //注册换肤功能
        SkinManager.getInstance().register(this);

        setContentView(R.layout.activity_personal_document);


        //去掉系统自带的标题栏
        ActionBar actionbar=getSupportActionBar();
        if(actionbar !=null)
        {
            actionbar.hide();
        }

        //获取年龄、性别等控件
        headculpture=findViewById(R.id.headculpture);
        userName=findViewById(R.id.userName);
        sex=findViewById(R.id.sex);
        age=findViewById(R.id.age);
        personalizedSignature=findViewById(R.id.personalizedSignature);

        //将左边的图标大小设置为0
        headculpture.setLeftIconSize(0);
        userName.setLeftIconSize(0);
        sex.setLeftIconSize(0);
        age.setLeftIconSize(0);
        personalizedSignature.setLeftIconSize(0);
    }

    public void refreash(){


        //获取用户的信息
        String name=getUser().getUserName();
        String sexinf=getUser().getSex();
        String ageinf=String.valueOf(getUser().getAge());
        String signature=getUser().getSignature();


        //注入年龄、性别等数据(为空使用默认数据)
        userName.setRightText(name);
        if(sexinf!=null)
            sex.setRightText(sexinf);
        else sex.setRightText("男");
        if(ageinf!=null)
            age.setRightText(ageinf);
        else age.setRightText("0");
        if (signature!=null)
            personalizedSignature.setRightText(signature);
        else personalizedSignature.setRightText("这个人什么都没留下");
    }
    public void setClickListeners(){
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(personalDocument.this, documentEditer.class);
                intent.putExtra("titleText","昵称");
                intent.putExtra("dataKey","name");
                Log.d("aaa","sex");
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
                intent.putExtra("dataKey","signature");
                startActivity(intent);
            }
        });
    }

}
