package cn.edu.cdut.jiemo.mine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.changeskin.SkinManager;

import java.io.FileNotFoundException;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.constraintlayout.widget.ConstraintLayout;

import cn.edu.cdut.jiemo.MainActivity;
import cn.edu.cdut.jiemo.R;
import cn.edu.cdut.jiemo.fragment.titleFragment;
import cn.edu.cdut.jiemo.login;

import static cn.edu.cdut.jiemo.mine.mineUserDao.getUser;

public class mine extends AppCompatActivity {
    Boolean isLogin;
    SharedPreferences pref;
    //头像的uri和其string类型
    private Uri imageUri;
    private String imageUriStr;
    //头像控件
    circleImageView userImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置布局
        setlayout();


        //设置更改主题、关于我们点击事件监听
        setClickListeners();

        //判断用户是否登录
        pref=getSharedPreferences("loginInfo",MODE_PRIVATE);
        isLogin=pref.getBoolean("isLogin",false);
        if(isLogin){
            //用户已经登录
            login();
        }else{
            //未登录
            unlogin();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        refreash();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //换肤功能注销
        SkinManager.getInstance().unregister(this);
    }


    //设置点击事件函数
    protected void setClickListeners(){

        ImageView returnbtn = findViewById(R.id.returnbtn);
        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mine.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //点击更改主题跳转到更改主题界面
        personalItemActivity changeTheme=findViewById(R.id.theme);
        changeTheme.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mine.this,changeTheme.class);
                startActivity(intent);
            }
        });

        //点击关于我们跳转到关于我们界面
        personalItemActivity aboutUs=findViewById(R.id.call);
        aboutUs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mine.this,aboutUs.class);
                startActivity(intent);
            }
        });
    }

    //界面设置操作
    protected void setlayout(){
        //注册换肤功能
        SkinManager.getInstance().register(this);
        setContentView(R.layout.activity_mine);

        //初始化控件
        userImage=findViewById(R.id.user_image);

        //去掉系统自带的标题栏
        ActionBar actionbar=getSupportActionBar();
        if(actionbar !=null)
        {
            actionbar.hide();
        }
        //设置导航栏宽度
        //
//        titleFragment titlefragment=(titleFragment)getSupportFragmentManager().findFragmentById(R.id.title_fragement);
//        titlefragment.setButtompadding(70);
    }

    //刷新界面的数据
    public void refreash(){
        //获取用户名称、头像uri数据
        String name=getUser().getUserName();
        isLogin=pref.getBoolean("isLogin",false);
        imageUriStr=getUser().getUserImage();
        if(isLogin){
            //设置用户名
            TextView userName=findViewById(R.id.userName);
            userName.setText(name);
            circleImageView user_imge=findViewById(R.id.user_image);
            user_imge.setImageResource(R.drawable.user_image);
            //设置头像
            if(imageUriStr!=null){
                //转换为Uri
                imageUri= Uri.parse(imageUriStr);
                userImage.setImageURI(imageUri);
            }
        }else{

        }
        //设置头像

    }

    //当用户登录时
    public void login(){
        SharedPreferences pref=getSharedPreferences("loginInfo",MODE_PRIVATE);
        String name=pref.getString("loginUserName","");


        //初始化用户数据Name
        getUser().initUser(getApplicationContext(),name);

        //点击头像跳转更改头像
        userImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mine.this, changeUserImage.class);
                startActivity(intent);
            }
        });

        //跳转到个人资料
        personalItemActivity accoount=findViewById(R.id.document);
        accoount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mine.this,personalDocument.class);
                startActivity(intent);
            }
        });
        //跳转到安全设置
        personalItemActivity safe=findViewById(R.id.safe);
        safe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mine.this,securitySetting.class);
                startActivity(intent);
            }
        });
        //退出登录
        Button exitBut=findViewById(R.id.exit);
        exitBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //清除sharepreference的内容
                SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putBoolean("isLogin", false);
                // 删除用户名
                editor.putString("loginUserName","");
                editor.commit();
                //恢复默认主题色
                SkinManager.getInstance().changeSkin("");
                //清除user的内容
                getUser().clearUser();
                //设置头像和用户名为未登录状态
                TextView userName=findViewById(R.id.userName);
                userName.setText("未登录");
                userImage=findViewById(R.id.user_image);
                userImage.setImageResource(R.drawable.toux);
                //执行未登录的方法
                unlogin();
            }
        });

    }

    //当用户未登录时
    public void unlogin(){
        //移除退出登录按钮
        ConstraintLayout window=findViewById(R.id.window);
        window.removeView(findViewById(R.id.exit));

        //点击头像跳转到登录界面
        circleImageView userimage=findViewById(R.id.user_image);
        userimage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mine.this, login.class);
                startActivity(intent);
            }
        });
        //点个人资料跳转到登录
        personalItemActivity accoount=findViewById(R.id.document);
        accoount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mine.this,login.class);
                startActivity(intent);
            }
        });
        //点安全设置跳转到登录
        personalItemActivity safe=findViewById(R.id.safe);
        safe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mine.this,login.class);
                startActivity(intent);
            }
        });

    }

}
