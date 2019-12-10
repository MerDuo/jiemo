package cn.edu.cdut.jiemo.mine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import cn.edu.cdut.jiemo.R;
import cn.edu.cdut.jiemo.fragment.titleFragment;

public class mine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("mine1", "onCreate: ");
        super.onCreate(savedInstanceState);
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

        //点击头像跳转到个人资料界面
        circleImageView userimage=findViewById(R.id.user_image);
        userimage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mine.this, personalDocument.class);
                startActivity(intent);
            }
        });


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
}
