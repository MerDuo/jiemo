package cn.edu.cdut.jiemo.mine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zhy.changeskin.SkinManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import cn.edu.cdut.jiemo.R;
import cn.edu.cdut.jiemo.fragment.titleFragment;

public class mine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("mine1", "onCreate: ");
        super.onCreate(savedInstanceState);

        //设置布局
        setlayout();

        //设置点击事件监听
        setOnClickListener();

        //记录用户启动程序的次数
        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        int count=pref.getInt("count",0);
        Log.d("mine1", "mineDebug"+count);

        if(count==0){
            //如果是第一次启动程序,存储用户数据
            editor.putString("name","Allo");
            editor.putInt("age",1);
            editor.putString("sex","女");
            editor.putString("words","....");
            editor.putInt("count",++count);
            editor.apply();
            Log.d("mine1", "save");
        }
        else{
            editor.putInt("count",++count);
            editor.apply();
            Log.d("mine1", "agin"+count++);
        }

        //设置用户名
        String name=pref.getString("name","");
        TextView userName=findViewById(R.id.userName);
        userName.setText(name);

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        String name=pref.getString("name","");
        TextView userName=findViewById(R.id.userName);
        userName.setText(name);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //换肤功能注销
        SkinManager.getInstance().unregister(this);
    }

    //跳转事件监听


//    @Override
//    public void onClick(View view) {
//        int id=view.getId();
//        switch (id){
//            case R.id.user_image:
//                //跳转到个人资料
//                Intent intent=new Intent(mine.this, personalDocument.class);
//                startActivity(intent);
//                break;
//            case R.id.aboutUs:
//                //跳转到关于我们
//                Intent intent2=new Intent(mine.this, aboutUs.class);
//                startActivity(intent2);
//                break;
//            case R.id.theme:
//                //跳转到更换主题
//                Intent intent3=new Intent(mine.this,changeTheme.class);
//                startActivity(intent3);
//                break;
//            case R.id.account:
//                //跳转到账号管理
//                Intent intent4=new Intent (mine.this,accountManage.class);
//                startActivity(intent4);
//                break;
//            case R.id.safe:
//                Intent intent5=new Intent (mine.this,accountManage.class);
//                startActivity(intent5);
//                break;
//        }


    //设置点击事件函数
    protected void setOnClickListener(){
        //点击头像跳转到个人资料界面
        circleImageView userimage=findViewById(R.id.user_image);
        userimage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mine.this, personalDocument.class);
                startActivity(intent);
            }
        });

        //跳转到账号管理
        personalItemActivity accoount=findViewById(R.id.account);
        accoount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mine.this,accountManage.class);
                startActivity(intent);
            }
        });
        //跳转到账号管理
        personalItemActivity safe=findViewById(R.id.safe);
        safe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mine.this,securitySetting.class);
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
    protected void setlayout(){
        //注册换肤功能
        SkinManager.getInstance().register(this);
        setContentView(R.layout.activity_mine);

        //去掉系统自带的标题栏
        ActionBar actionbar=getSupportActionBar();
        if(actionbar !=null)
        {
            actionbar.hide();
        }
        //设置导航栏宽度
        //
        titleFragment titlefragment=(titleFragment)getSupportFragmentManager().findFragmentById(R.id.title_fragement);
        titlefragment.setButtompadding(70);
    }

    //设置用户名
    public void setUserName(){
        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);

    }

}
